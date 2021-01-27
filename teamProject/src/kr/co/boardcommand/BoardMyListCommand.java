package kr.co.boardcommand;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.command.Command;
import kr.co.domain.BoardDAO;
import kr.co.ezen.BoardDTO;
import kr.co.ezen.PageTO;
import kr.co.util.CommandAction;

public class BoardMyListCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String sCurPage = request.getParameter("curPage");
		String id = request.getParameter("id");
		int curPage = 1;
		
		if (sCurPage != null) {
			curPage = Integer.parseInt(sCurPage);
		}
		
		BoardDAO dao = new BoardDAO();
		PageTO to = dao.myListPage(curPage, id);
		
		request.setAttribute("myList", to.getList());
		request.setAttribute("to", to);
		
		return new CommandAction(false, "bd_mylist.jsp");
	}

}