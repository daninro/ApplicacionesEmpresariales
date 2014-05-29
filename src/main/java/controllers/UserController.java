package controllers;

import java.sql.Date;
import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.user.UserService;
import user.User;
@Controller
public class UserController {
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@RequestMapping
	public void register(HttpServletRequest request){
		System.out.println("entramos en get");
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public String register(Model m, HttpServletRequest request){
		User u = new User(
				request.getParameter("name"), 
				Date.valueOf(request.getParameter("date")),
				request.getParameter("country") , 
				request.getParameter("email"),
				request.getParameter("password"), 
				request.getParameter("username")
			);
		User p = userService.addUser(u);
		if(u.getUsername().equals(p.getUsername())){
			return "redirect:/user/confirmation";
		}
		return "redirect:/user/bad_confirmation"; 
	}
	
	@RequestMapping
	public void search(Model m){
		
	}
	@RequestMapping
	public void bad_confirmation(Model m){
		m.addAttribute("results", "monga");
	}
}
