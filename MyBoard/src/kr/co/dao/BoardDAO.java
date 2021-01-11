package kr.co.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.domain.BoardDTO;
import kr.co.domain.PageTO;
import oracle.jdbc.proxy.annotation.Pre;

public class BoardDAO {

	private DataSource dataFatory;

	public BoardDAO() {

		try {
			Context ctx = new InitialContext();
			dataFatory = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle11g");

		} catch (NamingException e) {

			e.printStackTrace();
		}

	}

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {

		try {
			if (rs == null) {
				rs.close();
			}
			if (pstmt == null) {
				pstmt.close();
			}
			if (conn == null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(BoardDTO dto) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO myboard (num, author, title, content, repRoot, repStep, repIndent) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		// writeday , readcnt : sql에서 default값을 지정해뒀기 때문에 외부에서 입력해 줄 필요 없음

		try {

			conn = dataFatory.getConnection();
			int num = getNum(conn);

			// getNum() : NVL2가 자동으로 부여해준 num값을 도출해내는 메서드

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getAuthor());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, num);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);

			// 원글을 작성하는 경우, repRoot값은 글번호(num)값과 동일하기 때문에 파라미터에 num값을 주고
			// repStep은 답글간의 서열정리를 할 때 필요한 값이기 때문에 값을 줄 필요가 없고,
			// repIndent도 답글인 경우 들여쓰기를 하기 위해 지정한 컬럼이기 때문에 원글에서는 0을 값으로 주면 된다.

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
	}

	private int getNum(Connection conn) {

		int num = -1;
		// insert()에 num을 넘겨줘야하기 때문에, int num 변수를 먼저 설정해준다.
		// 메서드가 실행 완료되면 DB에서 num을 넘겨받아 return해준다.
		// (초기화 변수값이 -1 이 아니어도 상관없지만 보통 -1을 많이 씀)
		PreparedStatement pstmt = null;
		String sql = "SELECT NVL2(MAX(num), MAX(num)+1, 1) FROM myboard";

		// NVL2(MAX(num), MAX(num)+1, 1) 의 의미 :
		// myboard에서 max num(가장 큰 num값)값을 가져오는데, 만약 num값이 null이면 1을 가져오고
		// num값이 1이 아니면, max num에 1을 더해줘라

		// NVL2를 쓰는 이유? 자동으로 글 번호를 먹여주기 위해서 -> sql에서 따로 num값을 일일히 지정해주지 않아도 된다.
		// 새로 들어가는 글번호가 자동으로 현재 저장된 글 수 +1 되어서 저장됨

		ResultSet rs = null;

		try {

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1);
				// 가져와야 할 컬럼이 num뿐이므로 1이라고 해도 됨
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, null);
			// conn을 null로 지정한 이유?
			// getNum()의 경우, insert()에서 num값을 얻어내기 위해 만든 메서드이다
			// insert()안에서 커넥션풀을 빌려 실행된 후 conn을 다시 insert에게 넘겨줘야 하기 때문에
			// getNum()에서 conn을 닫아버리면 getNum()이후에 짜여진 코드들이 실행되지 못하고 종료되어 버린다.
		}
		return num;
	}

	public List<BoardDTO> list() {

		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT num, author, title, to_char(writeday,'yyyy/mm/dd') writeday, readcnt, repRoot, repStep, repIndent FROM myboard ORDER BY repRoot desc, repStep asc";

		// to_char(writeday,'yyyy/mm/dd') writeday : date형으로 받은 writeday의 표시형식을 연/월/일로
		// 바꿔줌
		// + 형식을 변경해준 뒤 별칭으로 writeday를 설정해, getString에서 가져올 수 있도록 함
		// 불러오기 할 때 반드시, ORDER BY repRoot desc, repStep asc !! :
		// 정렬조건 없이 리스트를 불러오면 num값 순서대로 정렬되기 때문에 원글과 답글이 묶여있지 못한다
		// 원글과 답글이 묶일 수 있도록 + 답글간의 서열을 정해 정렬할 수 있도록 정렬조건을 반드시 입력해 select문을 입력한다.

		try {
			conn = dataFatory.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int num = rs.getInt("num");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");

				BoardDTO dto = new BoardDTO(num, author, title, null, writeday, readcnt, repRoot, repStep, repIndent);
				// content를 호출하지 않은 이유 : 글 내용은 글 자세히 보기에서 확인할 수 있기 때문에
				// 글목록을 보여주는 list()에서는 content값을 가져올 필요가 없다.

				list.add(dto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}

		return list;
	}

	public BoardDTO read(int num) {

		BoardDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM myboard WHERE num = ? ";
		boolean isOK = false;
		/* 트랜잭션을 위해서 선언한 변수, isOK가 false값을 유지한다면 정상적으로 작입이 이루어졌다는 이야기 */
		// 트랜잭션을 왜 해야하나? read()가 실행되면, read()내부의 increaseReadcnt()도 실행되게 되는데
		// 두 메서드는 같은 자원(myboard)에 접근하여 각각의 요청에 대해 자원의 데이터를 가져오거나 변경한다.
		// (게시글 자세히보기를 클릭하면 -> 조회수가 1 오른다)
		// 이 때, 어딘가에서 작업이 중간에 중단되는 경우, 어느 한 메서드가 기능을 완료하지 못했는데 다른 메서드에서 지정해준 기능이 수행되면
		// 원하는 결과와 다른 값을 가지게 된다. -> 트랜잭션 상황이 발생한다.
		// 따라서, 트랜잭션 상황을 처리(rollback()이나 commit())해 실행하고자 했던 결과가 정상적으로 동작하도록 해준다.

		try {
			conn = dataFatory.getConnection();
			conn.setAutoCommit(false);
			// 트랜잭션 상황을 처리하기 위해서는 autocommit을 종료해줘야 한다. (false로 종료시킴)
			increaseReadcnt(conn, num);
			// 글 자세히보기를 할 때마다 조회수를 1씩 올려주는 메서드
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// read.jsp에서 ${dto.num}으로 값을 넘겨줬으므로, num을 이용해 찾기 :
				// num은 pk이기 때문에 검색결과도 단 하나만 나온다.
				String author = rs.getString("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");

				dto = new BoardDTO(num, author, title, content, writeday, readcnt, -1, -1, -1);

			}

			isOK = true;
			// 여기까지 작업이 정상적으로 이루어졌다면 isOK는 true로 값을 변경해준다

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			// isOK 가 true를 값으로 가지면 동작이 정상적으로 이루어졌다는 의미이므로 commit()
			// isOK 가 false를 값으로 가지면 동작이 정상적으로 이루어지지 않았다는 의미이므로 rollback()

			try {
				if (isOK) {
					conn.commit();
				} else {
					conn.rollback();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

			// 트랜잭션 순서 : boolean형의 isOK변수를 선언해 false로 초기화한다
			// 커넥션의 autocommit을 해제해준다 (파라미터로 false)
			// try문의 마지막 행에 isOK=true; 를 대입해준다. 코드가 정상적으로 실행되었다면 isOK는 true 값을 가진다
			// finally 블록에서 트랜잭션 처리를 한다.
			// 넘겨받은 isOK의 값이 true라면? -> commit();
			// 그렇지 않고 false라면 rollback();
			// 묶어서 try-catch를 해준다.

			closeAll(rs, pstmt, conn);
		}
		return dto;
	}

	private void increaseReadcnt(Connection conn, int num) {

		// 조회수를 1씩 올려주기 위해 사용하는 메서드.
		// num은 read()에서 넘겨받는 파라미터를 그대로 사용할 수 있도록 increas의 파라미터에도 num을 설정해준다

		PreparedStatement pstmt = null;
		// connection객체를 만들지 않아도 된다 : 파라미터로 넘겨받았기 때문
		String sql = "UPDATE myboard SET readcnt = readcnt +1 WHERE num = ?";

		// where 조건이 없이 update문을 작성하게 되면,
		// db에 저장되어있는 모든 데이터의 readcnt 수가 +1이 되므로 where조건을 반드시 넣어줄것
		// where 의 num 값은 read에서 넘어오는 파라미터 값을 그대로 쓸 수 있도록 inc~()에도 파라미터로 num값을 준다

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, null);
		}

	}

	public BoardDTO updateui(int num) {

		BoardDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM myboard WHERE num = ? ";

		try {
			conn = dataFatory.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				String author = rs.getString("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");
				

				dto = new BoardDTO(num, author, title, content, writeday, readcnt, repRoot, repStep, repIndent);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}

		return dto;
	}

	public void update(BoardDTO dto) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE myboard SET title = ?, author = ?, content=?, writeday=sysdate WHERE num=?";

		try {
			conn = dataFatory.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getAuthor());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getNum());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}

	}

	public void delete(int num) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM myboard WHERE num = ? ";

		try {
			conn = dataFatory.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
	}


	public List<BoardDTO> search(String searchOption, String searchKeyword) {

		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM myboard WHERE upper(" + searchOption + ") LIKE upper(?) ORDER BY repRoot desc, repStep asc ";
		
		// 문자열의 + 성질을 이용한다. searchOption 는 컬럼명을 대신하는 변수
		// 검색버튼을 통해 타고 온 jsp에서의 옵션값이 들어오게 된다. (ex, title/author/content...)
		// sql문을 = 로 주지 않는 이유 : 완전 일치한 결과만 가져오기 때문에 LIKE 키워드를 사용해야 함
		
		// upper(searchOption)을 지정하는 경우 : 입력한 키워드와 DB에서 가져올 키워드를 둘 다 대문자, 둘 다 소문자등 동일한 형식으로 만들어줘야함
		// 입력한 키워드는 대문자인데, DB에 소문자로 저장된 경우 검색이 안되는 것을 방지하기 위해서
		
		ResultSet rs = null;

		try {
			conn = dataFatory.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "%" + searchKeyword + "%");
			// 해당 키워드가 어디에 어떻게 들어있는지 정확히 알지 못할 때 %키워드% 형식으로 입력해주면
			// 키워드에 대한 정확한 정보가 없어도 해당 키워드가 확실히 포함되어 있다면 가져오는 것이 가능하다
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int num = rs.getInt("num");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repIndent = rs.getInt("repIndent");

				BoardDTO dto = new BoardDTO(num, author, title, null, writeday, readcnt, -1, -1, repIndent);
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}

		return list;
	}

	public void reply(int oriNum, BoardDTO repDTO) {
		// 답글을 달기 위해서는 원래글에 대한 정보가 필요함 : updateui()를 이용해서 가져옴
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO myboard (num, author, title, content, repRoot, repStep, repIndent) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		boolean isOK = false;
		// insert와 update가 동시에 진행되기 때문에 트랜잭션 상황이 발생할 가능성이 있으므로 트랜잭션 처리
		
		try {
			conn = dataFatory.getConnection();
			conn.setAutoCommit(false);
			int num = getNum(conn);
			BoardDTO oriDTO = updateui(oriNum);
			// getNum이나 updateui는 sql문을 SELECT 로 사용한다
			// 트랜잭션은 insert/delete/update의 경우에 발생하므로 select는 무관하다
			inceraseRepStepOtherReply(conn, oriDTO);
			// 같은 repRoot을 가진 repStep끼리의 서열정리를 위한 메서드
			// 원래 있던 답글이 새로 입력된 답글의 repStep값보다 같거나 크면 +1
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, repDTO.getAuthor());
			pstmt.setString(3, repDTO.getTitle());
			pstmt.setString(4, repDTO.getContent());
			// 답글이기 때문에 하기의 세가지 처리를 해줘야 한다
			pstmt.setInt(5, oriDTO.getRepRoot());
			// 1. 답글의 root값은 원글의 root값과 동일하다 ->oriDTO(원글)의 root 값을 그대로 가져옴
			pstmt.setInt(6, oriDTO.getRepStep()+1);
			// 2. 답글의 step값 : 원글의 step값 +1 (후처리 필요함 - increaseRepStepOtherReply())
			pstmt.setInt(7, oriDTO.getRepIndent()+1);
			// 3. 답글의 repIndent값 : 들여쓰기를 지정해 줄 수 있다. 
			// 원글은 indent값을 0으로, 답글의경우, 원글의 indent값+1을 해준다.
			pstmt.executeUpdate();
			
			isOK = true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (isOK) {
					conn.commit();
				} else {
					conn.rollback();
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			closeAll(null, pstmt, conn);
		}
	}

	private void inceraseRepStepOtherReply(Connection conn, BoardDTO oriDTO) {
		// 답글끼리의 서열을 정리해주는 메서드 
		PreparedStatement pstmt = null;
		String sql = "UPDATE myboard SET repStep = repStep+1 WHERE repRoot = ? AND repStep > ?";
		// repStep값을 +1하는데, 조건1 : repRoot가 같은 repStep값들 중에서 (repRoot = ?)
		// 조건2 : 새로 입력하는 repStep값이 원글의 repStep값 보다 큰 경우 (repStep > 원글의 repStep값) +1 한다.
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oriDTO.getRepRoot());
			pstmt.setInt(2, oriDTO.getRepStep());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, null);
		}
		
	}
	
	
	public PageTO page(int curPage) {
		
		PageTO to = new PageTO(curPage);		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		// pageTO에 페이징 작업을 해서 SELECT된 글들을 리스트로 불러와 화면에 출력하는 변수를 선언해주었기 때문에
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM(SELECT ROWNUM rnum, num, title, author, writeday, readcnt, repIndent "
				+ "FROM(SELECT * FROM myboard ORDER BY repRoot desc, repStep asc)) WHERE rnum >= ? AND rnum <= ?　";
		
		try {
			conn = dataFatory.getConnection();
			int amount = getAmount(conn);
			to.setAmount(amount);
			// amount는 독립변수이기 때문에 값을 반드시 넣어줘야 한다. : null이면 종속변수들의 값이 도출되지 않는다.
			// amount는 DB에 저장되어있는 데이터의 숫자를 불러오는것이기 때문에 DB와 연결해서 작업한다
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, to.getStartNum());
			pstmt.setInt(2, to.getEndNum());
			// setAmoun가 실행 완료 된 순간 -> setAmount 내부의 all()이 실행되면서
			// startNum과 endNum이 만들어졌기 떄문에 to객체를 통해 사용할 수 있다
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repIndent = rs.getInt("repIndent");
				
				BoardDTO dto = new BoardDTO(num, author, title, null, writeday, readcnt, -1, -1, repIndent);
				list.add(dto);
			
			}
			
			to.setList(list);
			// dto객체를 불러와 list에 넣어주고, 넣어준 리스트를 pageTO에서 선언한 list에 파라미터로 입력
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		
		return to;
		
	}

	private int getAmount(Connection conn) {
		int amount = 0;
		PreparedStatement pstmt = null;
		String sql = "SELECT COUNT(num) FROM myboard";
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				amount = rs.getInt(1);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return amount;
	}
	
}












