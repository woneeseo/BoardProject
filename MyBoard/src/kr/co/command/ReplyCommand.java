package kr.co.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.BoardDAO;
import kr.co.domain.BoardDTO;
import kr.co.domain.CommandAction;

public class ReplyCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String sNum = request.getParameter("num");
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		int oriNum = Integer.parseInt(sNum);
		// 원래글의 글 번호
		BoardDTO repDTO = new BoardDTO(-1, author, title, content, null, -1, -1, -1, -1);
		// 답글은 새로 입력되기 떄문에 번호를 sql에서 자동으로 부여해준다. : orinum 값을 주어서는 안됨
		new BoardDAO().reply(oriNum, repDTO);
		
		return new CommandAction(true, "list.do");
	}

}
