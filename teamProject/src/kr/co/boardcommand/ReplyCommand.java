package kr.co.boardcommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.command.Command;
import kr.co.domain.BoardDAO;
import kr.co.util.CommandAction;


import kr.co.ezen.BoardDTO;

public class ReplyCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String sNum = request.getParameter("num");
 		int oriNum = Integer.parseInt(sNum);

 		String id = request.getParameter("id");
 		String title = request.getParameter("title");
 		String content = request.getParameter("content");


 	    BoardDTO repDTO = new BoardDTO(-1, id, title, content, null, -1, -1, -1, -1);

 		new BoardDAO().reply(oriNum, repDTO);

 		return new CommandAction(true, "bd_list.do");
 	}
	

}
