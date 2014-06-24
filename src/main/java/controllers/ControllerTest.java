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

public class ControllerTest extends MyController{

	private MovieService movieService;
	/***services mutators***/
	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}
	
	
	/*ajax/test*/
	@RequestMapping
	public String test(Model m, HttpServletRequest request, HttpSession session){
		if(!isLogin(session)) return getLogin();
		List<Movie> l;
		try {
			int page = 1;
			if(request.getParameter("page")!=null){
				page = Integer.parseInt(request.getParameter("page"));
				if(page == 0) page++;
			}
			//l = movieService.getAllMovies(page, 20, (String)session.getAttribute("username"));
			m.addAttribute("movieList",l);
			m.addAttribute("prev", page - 1);
			m.addAttribute("next", page + 1);
		} catch (OperationUncompletedException e) {
			
		}
		return "ajax/test";
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
	
	
}
