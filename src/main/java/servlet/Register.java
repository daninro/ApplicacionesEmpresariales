package servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.user.UserServiceImplement;

public class Register extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		request.getRequestDispatcher("register.jsp").forward(request, response);
		
		//PrintWriter out = response.getWriter();
		//ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
		//UserServiceImplement us = (UserServiceImplement)context.getBean("userService");
	}
}