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
			return "redirect:/movie/list";
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
		String id = request.getParameter("id");
		System.out.println(id);
		String mark = request.getParameter(id+"_mark");
		movieService.setMark(Integer.parseInt(id), Integer.parseInt(mark), (String)session.getAttribute("username"));
		System.out.println(id);
		System.out.println(mark);
		
		
		return "redirect:/movie/list";
	}
	
}