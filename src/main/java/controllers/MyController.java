package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public abstract class MyController {

	private String login = "redirect:/user/login";
	
	protected String getLogin() {
		return login;
	}

	protected boolean isLogin(HttpSession session){
		if(session.getAttribute("username") == null) return false;
		return true;
	}
	
}
