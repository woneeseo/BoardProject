package kr.co.boardcommand;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.command.Command;
import kr.co.util.CommandAction;

import kr.co.ezen.LoginDTO;


public class BoardInsertUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			return new CommandAction(true, "loginui.do");
		}
		
	    LoginDTO login = (LoginDTO) session.getAttribute("login");
		
		if (login == null) {
			return new CommandAction(true, "loginui.do");
		}
		
		return new CommandAction(true, "bd_insert.jsp");
	}

}
