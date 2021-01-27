package kr.co.boardcommand;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.command.Command;
import kr.co.domain.BoardDAO;
import kr.co.util.CommandAction;

import kr.co.ezen.PageTO;


public class BoardListCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String sCurPage = request.getParameter("curPage");
		int curPage = 1;
		
		if (sCurPage != null) {
			curPage = Integer.parseInt(sCurPage);
		}
		BoardDAO dao =new BoardDAO();
		PageTO to = dao.page(curPage);
		
		request.setAttribute("list", to.getList());
		request.setAttribute("to", to);
		
		return new CommandAction(false, "bd_list.jsp");
	}

}
