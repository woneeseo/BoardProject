package kr.co.boardcommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.command.Command;
import kr.co.util.CommandAction;
import kr.co.ezen.BoardDTO;
import kr.co.domain.BoardDAO;

public class BoardContentReadCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String sNum = request.getParameter("num");
 		int num = 0;
 		try {
 			num = Integer.parseInt(sNum);
 		} catch (NumberFormatException e) {

 			e.printStackTrace();
 		}


 		BoardDAO dao = new BoardDAO();

 		BoardDTO dto = dao.read(num);

 		request.setAttribute("dto", dto);

 		return new CommandAction(false, "bd_read.jsp");
	}

}
