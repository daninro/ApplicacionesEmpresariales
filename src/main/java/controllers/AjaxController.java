package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import movie.Movie;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.movie.MovieService;
import user.User;
import exceptions.OperationUncompletedException;

public class AjaxController extends MyController{

	private MovieService movieService;
	/***services mutators***/
	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}
	
	
	
	/*ajax/mark*/
	@RequestMapping(method = {RequestMethod.POST})
	public String mark(Model m, HttpServletRequest request, HttpSession session){
		if(!isLogin(session)) return "ajax/empty";
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		int mark = Integer.parseInt(request.getParameter("mark"));
		try {
			movieService.setMark(movieId, mark, (String)session.getAttribute("username"));
		} catch (OperationUncompletedException e) {
			return "ajax/empty";
		}
		return "ajax/empty";
	}
	
	
	@RequestMapping(method = {RequestMethod.POST})
	public String ajaxdeletefromwishlist(Model m, HttpServletRequest request, HttpSession session){
		if(!isLogin(session)) return getLogin();
		String movie = request.getParameter("wl");
		System.out.println("aqui esta el id: " + movie);
		try {
			movieService.deleteMoviefromWishlist( Integer.parseInt(movie), (String)session.getAttribute("username"));
		} catch (OperationUncompletedException e) {
			return "redirect:index";
		}
		m.addAttribute("info", "pelicula quitada de la wishlist");
		return "/message/info";	
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public String ajaxaddwishlist(Model m, HttpServletRequest request, HttpSession session) throws OperationUncompletedException{
		if(!isLogin(session)) return getLogin();
		User u = (User) session.getAttribute("user");
		int movieid= Integer.parseInt(request.getParameter("name"));
		Movie mov = movieService.findMoviebyId(movieid);
				try {
					movieService.addWishlist(mov, u);
				} catch (OperationUncompletedException e) {
					m.addAttribute("info", "No se pudo agregar :(");
				}
		m.addAttribute("info", "agregada a la wishlist");
		return "message/info";
	}
	

	
	
}
