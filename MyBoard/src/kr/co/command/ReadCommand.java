package kr.co.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.BoardDAO;
import kr.co.domain.BoardDTO;
import kr.co.domain.CommandAction;

public class ReadCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String sNum = request.getParameter("num");
		
		int num = Integer.parseInt(sNum);
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.read(num);
		
		request.setAttribute("dto", dto);
		
		// DB에서 가져온 자료를 바인딩해서 jsp에 뿌려줘야, jsp에서 원하는 값을 화면에 띄워줄 수 있음
		
		return new CommandAction(false, "read.jsp");
	}

}
