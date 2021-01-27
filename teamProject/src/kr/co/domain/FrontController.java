package kr.co.domain;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.boardcommand.BoardContentReadCommand;
import kr.co.boardcommand.BoardDeleteCommand;

import kr.co.boardcommand.BoardInsertCommand;
import kr.co.boardcommand.BoardInsertUICommand;
import kr.co.boardcommand.BoardListCommand;
import kr.co.boardcommand.BoardMainCommand;
import kr.co.boardcommand.BoardMyListCommand;
import kr.co.boardcommand.BoardSearchCommand;
import kr.co.boardcommand.BoardUpdateCommand;
import kr.co.boardcommand.BoardUpdateUICommand;
import kr.co.boardcommand.ReplyCommand;
import kr.co.boardcommand.ReplyUICommand;

import kr.co.command.Command;
import kr.co.command.DeleteCommand;
import kr.co.command.FindIdCommand;
import kr.co.command.FindPWCommand;
import kr.co.command.FindUICommand;
import kr.co.command.InsertCommand;
import kr.co.command.InsertUICommand;
import kr.co.command.ListCommand;
import kr.co.command.LoginCommand;
import kr.co.command.LoginUICommand;
import kr.co.command.LogoutCommand;
import kr.co.command.ReadCommand;

import kr.co.command.UpdateCommand;
import kr.co.command.UpdateUICommand;
import kr.co.util.CommandAction;


/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cPath = request.getContextPath();
		String sp = uri.substring(cPath.length());
		Command com = null;
		
		if (sp.equalsIgnoreCase("/insertui.do")) {
			com = new InsertUICommand();		
		} else if(sp.equalsIgnoreCase("/insert.do")) {
			com = new InsertCommand();
		} else if(sp.equalsIgnoreCase("/read.do")) {
			com = new ReadCommand();
		} else if(sp.equalsIgnoreCase("/updateui.do")) {
			com = new UpdateUICommand();
		} else if(sp.equalsIgnoreCase("/update.do")) {
			com = new UpdateCommand();
		} else if (sp.equalsIgnoreCase("/loginui.do")) {
			com = new LoginUICommand();
		} else if (sp.equalsIgnoreCase("/login.do")) {
			com = new LoginCommand();
		}  else if (sp.equalsIgnoreCase("/logout.do")) {
			com = new LogoutCommand();
		} else if (sp.equalsIgnoreCase("/delete.do")) {
			com = new DeleteCommand();
		} else if (sp.equalsIgnoreCase("/list.do")) {
			com = new ListCommand();
		} else if (sp.equalsIgnoreCase("/findui.do")) {
			com = new FindUICommand();
		} else if (sp.equalsIgnoreCase("/findid.do")) {
			com = new FindIdCommand();
		} else if (sp.equalsIgnoreCase("/findpw.do")) {
			com = new FindPWCommand();
		} else if (sp.equalsIgnoreCase("/bd_list.do")) {
			com = new BoardListCommand();
		} else if (sp.equalsIgnoreCase("/bd_search.do")) {
			com = new BoardSearchCommand();
		} else if (sp.equalsIgnoreCase("/bd_insertui.do")) {
			com = new BoardInsertUICommand();
		} else if (sp.equalsIgnoreCase("/bd_insert.do")) {
			com = new BoardInsertCommand();
		} else if (sp.equalsIgnoreCase("/bd_read.do")) {
			com = new BoardContentReadCommand();
		} else if (sp.equalsIgnoreCase("/bd_delete.do")) {
			com = new BoardDeleteCommand();
		} else if (sp.equalsIgnoreCase("/bd_reply.do")) {
			com = new ReplyCommand();
		} else if (sp.equalsIgnoreCase("/bd_replyui.do")) {
			com = new ReplyUICommand();
		} else if (sp.equalsIgnoreCase("/bd_update.do")) {
			com = new BoardUpdateCommand();
		} else if (sp.equalsIgnoreCase("/bd_updateui.do")) {
			com = new BoardUpdateUICommand();
		} else if (sp.equalsIgnoreCase("/main.do")) {
			com = new BoardMainCommand();
		} else if (sp.equalsIgnoreCase("/bd_mylist.do")) {
			com = new BoardMyListCommand();
		} 
		
		if(com != null) {
			CommandAction action = com.execute(request, response);
			
			if(action.isRedirect()) {
				response.sendRedirect(action.getWhere());
			}else {
				request.getRequestDispatcher(action.getWhere())
				.forward(request, response);
			}
		}
		
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
