package kr.co.domain;

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



import kr.co.ezen.LoginDTO;
import kr.co.ezen.MemberDTO;




public class MemberDAO {
	private DataSource dataFactory;
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle11g");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void CloseAll(ResultSet rs, Connection conn, PreparedStatement pstmt) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public LoginDTO login(LoginDTO loginDTO) {
		LoginDTO login = new LoginDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from mb_tbl where id = ? and pw = ?";
		ResultSet rs = null;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginDTO.getId());
			pstmt.setString(2, loginDTO.getPw());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				login = new LoginDTO();
				login.setId(loginDTO.getId());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseAll(rs, conn, pstmt);
		}
		return login;
	}
	public boolean loginCheck(LoginDTO dto) {
		boolean check = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from mb_tbl where id = ? and pw = ?";
		ResultSet rs = null;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			rs = pstmt.executeQuery();
			check = rs.next();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseAll(rs, conn, pstmt);
		}
		
		return check;
		
	}
	public String findId(String name, String email) {
		String id = null;
	
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select id from mb_tbl where name = ? and email = ?";
		ResultSet rs = null;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id = rs.getString("id");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseAll(rs, conn, pstmt);
		}
		
		
		return id;
	}
	public String findPw(String name, String email) {
		String pw = null;
	
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select pw from mb_tbl where name = ? and email = ?";
		ResultSet rs = null;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pw = rs.getString("pw");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseAll(rs, conn, pstmt);
		}
		
		
		return pw;
	}
	public void delete(String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM mb_tbl WHERE id=?";

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			CloseAll(null, conn, pstmt);
		}
	}

	public List<MemberDTO> list() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM mb_tbl";
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String pw = rs.getString("pw");
				String birth = rs.getString("birth");
				int tel = rs.getInt("tel");
				String address = rs.getString("address");

				MemberDTO dto = new MemberDTO(id, name, email, pw, birth, tel,address);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseAll(rs, conn, pstmt);
		}
		return list;
	}

	public MemberDTO read(String id) {
		MemberDTO dto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM mb_tbl WHERE id = ?";
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String pw = rs.getString("pw");
				String date = rs.getString("birth");
				int tel = rs.getInt("tel");
				String address = rs.getString("address");

				dto = new MemberDTO(id, name, email, pw, date, tel,address);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseAll(rs, conn, pstmt);
		}

		return dto;
	}

	public void insert(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO mb_tbl (id, name, email, pw, birth, tel,address) VALUES (?,?,?,?,?,?,?)";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getPw());
			pstmt.setString(5, dto.getBirth());
			pstmt.setInt(6, dto.getTel());
			pstmt.setString(7, dto.getAddress());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseAll(null, conn, pstmt);
		}
	}

	
	public void update(MemberDTO dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE mb_tbl SET name = ?, pw = ?, email = ?, birth = ?, tel = ?, address = ? WHERE id = ?";
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getBirth());
			pstmt.setInt(5, dto.getTel());
			pstmt.setString(6, dto.getAddress());
			pstmt.setString(7, dto.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseAll(null, conn, pstmt);
		}
	}

	public MemberDTO updateui(String id) {
		
		MemberDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT name, pw, email, to_char(birth, 'yyyy-mm-dd') birth, tel,address FROM mb_tbl WHERE id = ? ";
		
		try {
			
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				String name = rs.getString("name");
				String pw = rs.getString("pw");
				String email = rs.getString("email");
				String birth = rs.getString("birth");
				int tel = rs.getInt("tel");
				String address = rs.getString("address");
				dto = new MemberDTO(id, name, email, pw, birth, tel,address);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseAll(rs, conn, pstmt);
		}
		return dto;
	}


	public MemberDTO idCheck(String id) {
		MemberDTO dto = null;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "SELECT * FROM mb_tbl WHERE id = ?";
	      ResultSet rs = null;
	      
	      try {
	         conn = dataFactory.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, id);
	         rs = pstmt.executeQuery();
	         
	         if (rs.next()) {
	            String name = rs.getString("name");
	            String email = rs.getString("email");
	            String pw = rs.getString("pw");
	            String birth = rs.getString("birth");
	            int tel = rs.getInt("tel");   
	            String address = rs.getString("address");
	            dto = new MemberDTO(id, name, email, pw, birth, tel,address);
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         CloseAll(rs, conn, pstmt);
	      }
	      return dto;

}
}





