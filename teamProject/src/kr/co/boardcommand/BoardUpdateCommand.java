package kr.co.boardcommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.command.Command;
import kr.co.domain.BoardDAO;
import kr.co.util.CommandAction;
import kr.co.ezen.BoardDTO;

public class BoardUpdateCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String sNum = request.getParameter("num");
 		int num = -1;
 		try {
 			num = Integer.parseInt(sNum);
 		} catch (NumberFormatException e) {

 			e.printStackTrace();
 		}

 		String id = request.getParameter("id");
 		String title = request.getParameter("title");
 		String content = request.getParameter("content");

 		BoardDTO dto = new BoardDTO(num, id, title, content, null, -1, -1, -1, -1);

 		new BoardDAO().update(dto);

 		return new CommandAction(true, "bd_list.do");
	}

}
