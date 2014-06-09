package controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import movie.Movie;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exceptions.OperationUncompletedException;
import service.movie.MovieService;


public class MovieController extends MyController{
	private MovieService movieService;
	/***services mutators***/
	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
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
		
				Movie p = null;
				try {
					p = movieService.addMovie(mov);
				} catch (OperationUncompletedException e) {
					//incompleto
					System.out.println("enviar a pagina de error");
				}
				if(p != null){
			return "redirect:/movie/list";
		}
		return "redirect:/user/bad_confirmation"; 
	}
		
	/***movie/list list movie***/
	@RequestMapping
	public String list(Model model, HttpSession session){
		if(!isLogin(session)) return getLogin();
		List<Movie> list = null;
		try {
			list = movieService.getAllMovies();
		} catch (OperationUncompletedException e) {
			//incompleto
			System.out.println("enviar a pagina de error con e.getMessage");
		}
		model.addAttribute("movieList",list);
		return "/movie/list";
	}	
	/**movie/search movie search***/
	
	@RequestMapping
	public String search(Model m, HttpSession session){
		if(!isLogin(session)) return getLogin();
		return "movie/search";
	}
	
	/***movie/addmovie getform***/
	@RequestMapping(method = {RequestMethod.POST})
	public String search(Model m, HttpServletRequest request, HttpSession session){
		if(!isLogin(session)) return getLogin();
		String name = request.getParameter("name");
		List<Movie> l = null;
		try {
				l = movieService.searchByName(name);
				} catch (OperationUncompletedException e) {
					
					//incompleto
					System.out.println("enviar a pagina de error");
					return "movie/search";
				}
		if(!l.isEmpty()) {
			m.addAttribute("movieList",l);
		}
		return "movie/search";
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public String table_search(Model m, HttpServletRequest request, HttpSession session){
		if(!isLogin(session)) return getLogin();
		String name = request.getParameter("name");
		List<Movie> l = null;
		try {
				l = movieService.searchByName(name);
				} catch (OperationUncompletedException e) {
					//incompleto
					System.out.println("enviar a pagina de error");
					return "movie/search";
				}
		if(!l.isEmpty()) {
			m.addAttribute("movieList",l);
		}
		System.out.println(m.containsAttribute("movieList"));
		return "movie/table_search";
	}
	
		
	
	
	
	
	@RequestMapping
	public String mark(Model model, HttpSession session){
		if(!isLogin(session)) return getLogin();
		List<Movie> list = null;
		try {
			list = movieService.getAllMovies();
		} catch (OperationUncompletedException e) {
			//incompleto
			System.out.println("enviar a pagina de error con e.getMessage");
		}
		model.addAttribute("movieList",list);
		return "/movie/mark";
	}	

	@RequestMapping(method = {RequestMethod.POST})
	public String mark(Model model, HttpSession session, HttpServletRequest request){
		if(!isLogin(session)) return getLogin();
		String id = request.getParameter("id");
		System.out.println(id);
		String mark = request.getParameter(id+"_mark");
		
		
		try {
			movieService.setMark(Integer.parseInt(id), Integer.parseInt(mark), (String)session.getAttribute("username"));
		} catch (NumberFormatException e) {
			//problemas con la transformacion numerica, javascripts deberia preocuparse (la interfaz)
			
		} catch (OperationUncompletedException e) {
			//incompleto
			System.out.println("enviar a pagina de error con e.getMessage");
		}
		return "redirect:/movie/list";
	}
	
}