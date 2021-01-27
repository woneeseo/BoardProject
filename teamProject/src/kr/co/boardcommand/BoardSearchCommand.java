package kr.co.boardcommand;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.command.Command;

import kr.co.domain.BoardDAO;
import kr.co.util.CommandAction;


import kr.co.ezen.BoardDTO;

public class BoardSearchCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String searchOption = request.getParameter("searchOption");
		String searchKeyword = request.getParameter("searchKeyword");
		
		List<BoardDTO> list = new BoardDAO().search(searchOption,searchKeyword);
		
		request.setAttribute("list", list);
		
		return new CommandAction(false, "bd_list.jsp");
	}

}
