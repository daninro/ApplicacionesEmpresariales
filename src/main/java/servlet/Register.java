package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.user.UserServiceImplement;
import user.User;

public class Register extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
			request.getRequestDispatcher("register.jsp").forward(request, response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
		UserServiceImplement us = (UserServiceImplement)context.getBean("userService"); 

		User u = us.addUser(	new User(
									request.getParameter("name"), 
									Date.valueOf(request.getParameter("date")),
									request.getParameter("country") , 
									request.getParameter("email"),
									request.getParameter("password"), 
									request.getParameter("username")
								)
							);
		PrintWriter out = response.getWriter();
		
		
		out.println(u.toString());
		
		
		
	}
}