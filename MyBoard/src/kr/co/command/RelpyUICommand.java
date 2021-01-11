package kr.co.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.domain.CommandAction;

public class RelpyUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String sNum = request.getParameter("num");
		
		return new CommandAction(true, "reply.jsp?num="+sNum);
		// int형으로 num값을 변환하지 않은 이유 : 어짜피 문자열과 숫자를 더하면 문자열로 인식됨
	}

}
