package kr.co.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.domain.MemberDAO;
import kr.co.ezen.MemberDTO;
import kr.co.util.CommandAction;

public class UpdateCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String birth = request.getParameter("birth");
		String email = request.getParameter("email");
		String sTel = request.getParameter("tel");
		String address = request.getParameter("address");
		int tel = Integer.parseInt(sTel);
		
		MemberDTO dto = new MemberDTO(id, name, email, pw, birth, tel,address);
	
		new MemberDAO().update(dto);
		
		return new CommandAction(true, "read.do?id="+id);
	}

}