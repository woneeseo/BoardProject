package kr.co.command;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.BoardDAO;
import kr.co.domain.BoardDTO;
import kr.co.domain.CommandAction;

public class InsertCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO(-1, author, title, content, null, 0, 0, 0, 0);
		
		// num, writeday, readcnt, repRoot, repStep, repIndent : sql에서 자동으로 입력되기 때문에 null이나 0을 지정해줘도 ok
		
		dao.insert(dto);

		
		return new CommandAction(true, "list.do");
	}

}
