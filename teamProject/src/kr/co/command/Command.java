package kr.co.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public interface Command {
	public abstract kr.co.util.CommandAction execute(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException;
}
