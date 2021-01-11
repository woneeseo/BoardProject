package kr.co.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.BoardDAO;
import kr.co.domain.BoardDTO;
import kr.co.domain.CommandAction;
import kr.co.domain.PageTO;

public class ListCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String sCurPage = request.getParameter("curPage");
		// 클릭으로 파라미터를 넘겨받는 경우 
		int curPage = 1;
		// 페이지 정보를 넘겨받지 못했을 때 1을 디폴트값으로 해서 항상 1페이지를 띄우도록 함
		
		if (sCurPage != null) {
			
			curPage = Integer.parseInt(sCurPage);
		}

		BoardDAO dao = new BoardDAO();
		PageTO to = dao.page(curPage);
		
		
		// DB에서 가져온 데이터를 바인딩 해서 jsp에 뿌려줘야함.
		request.setAttribute("list", to.getList());
		// to 객체 안에는 list말고도 여러가지 변수가 있기 때문에, getList()로 list객체만을 불러와 바인딩 해줄 수 있다
		// to.getList()로 바인딩을 하면 list.jsp를 수정 할 필요가 없다.
		
		request.setAttribute("to", to);
		// to.getList()를 바인딩 하게 되면, 페이징에 필요한 다른 변수들을 사용할 수 없다
		// 따라서 페이징에 필요한 요소들을 사용하기 위해, to객체로도 바인딩을 한번 더 해준다.
		
		return new CommandAction(false, "list.jsp");
	}

}
