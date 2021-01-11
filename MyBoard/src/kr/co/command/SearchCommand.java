package kr.co.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.BoardDAO;
import kr.co.domain.BoardDTO;
import kr.co.domain.CommandAction;

public class SearchCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String searchOption = request.getParameter("searchoption");
		String searchKeyword = request.getParameter("searchkeyword");
		
		// search 버튼을 클릭하면, searchoption 과 searchkeyword 로 파라미터 값을 넘겨준다
		// search.do?searchoption=title&searchkeyword=%EC%95%88
		// 따라서 search커맨드에서는 파라미터로 searchoption 과 searchkeyword을 가져와야 한다
		
		List<BoardDTO> list = new BoardDAO().search(searchOption, searchKeyword);
		
		request.setAttribute("list", list);
		
		return new CommandAction(false, "search.jsp");
	}

}
