package kr.co.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.BoardDAO;
import kr.co.domain.BoardDTO;
import kr.co.domain.CommandAction;

public class UpdateUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String sNum = request.getParameter("num");
		int num = Integer.parseInt(sNum);
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.updateui(num);
		
		request.setAttribute("dto", dto);
		// update.jsp에 데이터를 넘겨줘야 하므로 바인딩 해야함
		
		return new CommandAction(false, "update.jsp");
	}

}
