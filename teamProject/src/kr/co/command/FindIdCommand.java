package kr.co.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.domain.MemberDAO;
import kr.co.util.CommandAction;


public class FindIdCommand implements Command{

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String id = new MemberDAO().findId(name, email);
		if (id == null) {
			return new CommandAction(true, "find.jsp");
		}
		HttpSession session=request.getSession();
		session.setAttribute("id", id);
		
		return new CommandAction(false, "findid.jsp");
	}

}
