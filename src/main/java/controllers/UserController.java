package controllers;

import java.sql.Date;
import java.util.List;

import javax.servlet.ServletRequest;
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
	public String register(Model m, ServletRequest request, HttpSession session){
	
		if(isLogin(session)){
			User u = null;
			try{
	    	u = userService.getUserbyUsername(request.getParameter("username"));
	    	if(u.isAdmin())
	    		m.addAttribute("user",u);
			}catch(Exception e){}
		}
		
	return "user/register";
		
	}
	
	
	@RequestMapping(method = {RequestMethod.POST})
	public String register(Model m, HttpServletRequest request, HttpSession session){
		String admin = request.getParameter("isAdmin");
		System.out.println("aqui "+admin);
		User u = new User(
				request.getParameter("name"), 
				Date.valueOf(request.getParameter("date")),
				request.getParameter("country") , 
				request.getParameter("email"),
				request.getParameter("password"), 
				request.getParameter("username"),
				(admin!=null)?true:false
			);
		try{
			User p = userService.addUser(u);
			if(u.getUsername().equals(p.getUsername())){
				m.addAttribute("message", "usuario agregado");
				
				System.out.println(u.isAdmin());
				if(!u.isAdmin()){
					
				u = null;
				try {
					u = userService.getUserbyUsername(request.getParameter("username"));
				} catch (OperationUncompletedException e) {
				}
				if(u!=null){
					if(u.getPassword().compareTo(request.getParameter("password"))==0){
						session.setAttribute("user", u);
						session.setAttribute("username", u.getUsername());
						session.setAttribute("counter", 10);
						System.out.println(u.getName());
						m.addAttribute("user", u);
						return "redirect:/movie/init";
					}
				}
				}
				else{
					return "user/register";
				}
				
				m.addAttribute("message", "problemas logeandote");
				return "/message/message";
				
				
			}
		}catch(OperationUncompletedException e){
			m.addAttribute("message", e.getMessage());
		}
		return "/message/message"; 
	}
	
	@RequestMapping
	public String login(HttpServletRequest request, Model m, HttpSession session){
		if(isLogin(session)) return "redirect:/movie/list";
		return "user/login";
		
	}
	
	@RequestMapping (method = {RequestMethod.POST})
	public String login(Model m, HttpServletRequest request, HttpSession session){
		
		User u = null;
		
		try {
			u = userService.getUserbyUsername(request.getParameter("username"));
		} catch (OperationUncompletedException e) {
			
		}
		if(u!=null){
			if(u.getPassword().compareTo(request.getParameter("password"))==0){
				session.setAttribute("user", u);
				session.setAttribute("username", u.getUsername());
				
				System.out.println(u.getName());
				return "redirect:/movie/list";
				
			}
		}
		m.addAttribute("message", "problemas logeandote");
		return "/message/message";
	} 
	@RequestMapping
	public String logout(Model m, HttpSession session){
		session.removeAttribute("username");
		session.removeAttribute("user");
		return "redirect:/user/login";
	}
	
	@RequestMapping
	public String index(Model m, HttpSession session, ServletRequest request){
		if(!isLogin(session)) return getLogin();
		User u = null;
		try {
			u = userService.getUserbyUsername(request.getParameter("username"));
			m.addAttribute("user",u);
			
		} catch (OperationUncompletedException e) {
			
		}	
		return "/index";
	}

	@RequestMapping
	public String deleteuser(Model model, HttpSession session){
		if(!isLogin(session)) return getLogin();
		List<User> user = null;
		try {
			user = userService.getAllUser();
		} catch (OperationUncompletedException e) {
			//incompleto
			System.out.println("enviar a pagina de error con e.getMessage");
		}
		model.addAttribute("users",user);
		return "/user/deleteuser";
	}	
	
	
	
	
	
	@RequestMapping(method = {RequestMethod.POST})
	public String deleteuser(Model m, HttpServletRequest request, HttpSession session){
		if(!isLogin(session)) return getLogin();
		String user = request.getParameter("user"); 
			try {
			userService.deleteUser(user);
		} catch (OperationUncompletedException e) {
			
		}
	return "/user/deleteuser";
	}
	
	@RequestMapping
	public void edituser(Model model, HttpServletRequest request,  HttpSession session){
		User u = (User)session.getAttribute("user");
		model.addAttribute("user",u);
		
		
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public String edituser(Model model, HttpSession session, HttpServletRequest request) throws OperationUncompletedException{
		if(!isLogin(session)) return getLogin();
		User u = new User(
				request.getParameter("name"), 
				Date.valueOf(request.getParameter("date")),
				request.getParameter("country") , 
				request.getParameter("email"),
				request.getParameter("password"), 
				request.getParameter("user"),
				Boolean.valueOf(request.getParameter("isAdmin"))
			);
			userService.editUser(u);
			model.addAttribute("user", u);
			session.removeAttribute("user");
			session.setAttribute("user", u);
		return "user/edituser"; 
	}
	
	@RequestMapping
	public String admin(Model m, ServletRequest request, HttpSession session){
		if(!isLogin(session)) return getLogin();
		return "/user/admin";
		
	}
	
	

}
