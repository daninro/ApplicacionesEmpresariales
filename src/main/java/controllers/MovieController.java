package controllers;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import movie.Movie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.movie.MovieService;

@Controller
public class MovieController {
	private MovieService movieService;

	
	public MovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}
	@RequestMapping
	public void addmovie( HttpServletRequest request){}
	
	@RequestMapping(method = {RequestMethod.POST})
	public String addmovie(Model m, HttpServletRequest request){
		
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
	
	@RequestMapping
	public void confirmation( HttpServletRequest request){}
	
	@RequestMapping
	public void list(Model model){
		List<Movie> list = movieService.getAllMovies();
		model.addAttribute("movieList",list);
	}	
	
}