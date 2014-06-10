package controllers;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exceptions.OperationUncompletedException;
import service.user.UserService;
import user.User;

public class UserController extends MyController{
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@RequestMapping
	public void register(){}
	
	@RequestMapping(method = {RequestMethod.POST})
	public String register(Model m, HttpServletRequest request){
		User u = new User(
				request.getParameter("name"), 
				Date.valueOf(request.getParameter("date")),
				request.getParameter("country") , 
				request.getParameter("email"),
				request.getParameter("password"), 
				request.getParameter("username"),
				false
			);
		try{
			User p = userService.addUser(u);
		
			if(u.getUsername().equals(p.getUsername())){
				m.addAttribute("message", "usuario agregado");
				return "/message/message";
			}
		}catch(OperationUncompletedException e){};
		m.addAttribute("message", "no pudimos :(");
		return "/message/message"; 
	}
	
	@RequestMapping
	public void login(HttpServletRequest request, Model m){}
	
	@RequestMapping (method = {RequestMethod.POST})
	public String login(Model m, HttpServletRequest request, HttpSession session){
		if(isLogin(session)) return "redirect:/movie/list";
		User u = null;
		u = userService.getUserbyUsername(request.getParameter("username"));
		if(u!=null){
			if(u.getPassword().compareTo(request.getParameter("password"))==0){
				System.out.println(u.getPassword());
				session.setAttribute("username", u.getUsername());
				return "redirect:/user/index";
			}
		}
		m.addAttribute("message", "problemas logeandote");
		return "/message/message";
	} 
	@RequestMapping
	public String logout(Model m, HttpSession session){
		session.removeAttribute("username");
		return "redirect:/user/login";
	}
	
	@RequestMapping
	public String index(Model m, HttpSession session){
		if(!isLogin(session)) return getLogin();
		return "/index";
	}
	
	
	
}
