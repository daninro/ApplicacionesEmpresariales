package controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import movie.Movie;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.movie.MovieService;
import service.user.UserService;


public class MovieController extends MyController{
	private MovieService movieService;
	/***services mutators***/
	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}
	public void setUserService(UserService userService) {
	}
	
	
	/***movie/addmovie view form***/
	@RequestMapping
	public String addmovie(Model m, HttpSession session){
		if(!isLogin(session)) return getLogin();
		return "movie/addmovie";
	}
	
	/***movie/addmovie getform***/
	@RequestMapping(method = {RequestMethod.POST})
	public String addmovie(Model m, HttpServletRequest request, HttpSession session){
		if(!isLogin(session)) return getLogin();
		 
		Movie mov = new Movie(
				request.getParameter("name"), 
				Integer.parseInt(request.getParameter("year")), 
				Integer.parseInt(request.getParameter("running_time")),
				request.getParameter("country"), 
				Integer.parseInt(request.getParameter("budget")), 
				Integer.parseInt(request.getParameter("box_office"))
				);
		
				Movie p = movieService.addMovie(mov);
				if(p != null){
			return "redirect:/user/confirmation";
		}
		return "redirect:/user/bad_confirmation"; 
	}
	
	
	/***movie/list list movie***/
	@RequestMapping
	public String list(Model model, HttpSession session){
		if(!isLogin(session)) return getLogin();
		List<Movie> list = movieService.getAllMovies();
		model.addAttribute("movieList",list);
		return "/movie/list";
	}	
	
	@RequestMapping
	public String mark(Model model, HttpSession session){
		if(!isLogin(session)) return getLogin();
		List<Movie> list = movieService.getAllMovies();
		model.addAttribute("movieList",list);
		return "/movie/mark";
	}	
	@RequestMapping(method = {RequestMethod.POST})
	public String mark(Model model, HttpSession session, HttpServletRequest request){
		if(!isLogin(session)) return getLogin();
		List<Movie> list = movieService.getAllMovies();
		for(int i = 0; i < list.size(); i++){
			String mark = request.getParameter(list.get(0).getId()+ "_mark");
			movieService.setMark(list.get(0).getId(), Integer.parseInt(mark), session.getAttribute("username").toString());
			
		}
		model.addAttribute("movieList",list);
		return "redirect:/movie/list";
	}
	
}