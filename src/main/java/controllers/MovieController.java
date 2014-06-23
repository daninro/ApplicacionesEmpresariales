package controllers;

import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import movie.Movie;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exceptions.OperationUncompletedException;
import service.movie.MovieService;
import service.actor.ActorService;
import actor.Actor;
import user.User;



public class MovieController extends MyController{
	private MovieService movieService;
	/***services mutators***/
	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}
	
	private ActorService actorService;
	/***services mutators***/
	public void setActorService(ActorService actorService) {
		this.actorService = actorService;
	}
	
	
	/***movie/addmovie view form***/
	@RequestMapping
	public String addmovie(Model m, HttpSession session){
		if(!isLogin(session)) return getLogin();
		User u = (User) session.getAttribute("user");
		if(u.isAdmin()){
		return "movie/addmovie";}
		return "redirect:/movie/list";
	}
	
	/***movie/addmovie getform***/
	@RequestMapping(method = {RequestMethod.POST})
	public String addmovie(Model m, HttpServletRequest request, HttpSession session){
		if(!isLogin(session)) return getLogin();
			
		Movie mov = new Movie(
				request.getParameter("name"), 
				Integer.parseInt(request.getParameter("year")), 
				request.getParameter("country"),
				request.getParameter("image")
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
	public String moviedetails(Model model, HttpSession session, HttpServletRequest request){
		if(!isLogin(session)) return getLogin();
		Movie m= null;
		try {
			m = movieService.findMoviebyId(5);
		} catch (OperationUncompletedException e) {
			//incompleto
			System.out.println("enviar a pagina de error con e.getMessage");
		}
		model.addAttribute("movieDetails",m);
		return "movie/moviedetails";
	}
	

	
	@RequestMapping(method = {RequestMethod.POST})
	public String moviedetails(Model m, HttpServletRequest request, HttpSession session) throws OperationUncompletedException{
		if(!isLogin(session)) return getLogin();
		User u = (User) session.getAttribute("user");
		int movieid= Integer.parseInt(request.getParameter("name"));
		Movie mov = movieService.findMoviebyId(movieid);
		
				List <Movie>p = null;
				try {
					System.out.println(mov.getId());
					System.out.println(u.getUsername());
					System.out.println("la priemra cosa");
					p = movieService.addWishlist(mov, u);
					System.out.println(mov.toString());
					System.out.println(u.toString());
					System.out.println("la segunda cosa");

				} catch (OperationUncompletedException e) {
					//incompleto
					System.out.println("enviar a pagina de error asdasdsad" + e.toString());
				}
		m.addAttribute("movieDetails", p);
		return "movie/moviedetails2";

	}

	@RequestMapping
	public String top20(Model model, HttpSession session){
		if(!isLogin(session)) return getLogin();
		List<Movie> list = null;
		try {
			list = movieService.top20();
			
		} catch (OperationUncompletedException e) {
			//incompleto
			System.out.println("enviar a pagina de error con e.getMessage");
		}
		model.addAttribute("top20",list);
		return "/movie/top20";
	}	
	
	@RequestMapping
	public String last10(Model model, HttpSession session){
		if(!isLogin(session)) return getLogin();
		List<Movie> list = null;
		try {
			list = movieService.last10();
			System.out.println(list.get(0).getName());
		} catch (OperationUncompletedException e) {
			//incompleto
			System.out.println("enviar a pagina de error con e.getMessage");
		}
		model.addAttribute("last10",list);
		return "/movie/last10";
	}	

	@RequestMapping
	public String editmovie(Model model, HttpServletRequest request,  HttpSession session){
		if(!isLogin(session)) return getLogin();
		User u = (User) session.getAttribute("user");
		Movie m= null;
		try {
//agarrar la película que viene de la pag anterior a la edición, en vez del 5
			m = movieService.findMoviebyId(5);
		} catch (OperationUncompletedException e) {
			//incompleto
			System.out.println("enviar a pagina de error con e.getMessage");
		}
		model.addAttribute("movieDetails",m);
		
		if(u.isAdmin()){
			return "movie/editmovie";}
			return "redirect:index";
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	public String editmovie(Model model, HttpSession session, HttpServletRequest request) throws OperationUncompletedException{
		if(!isLogin(session)) return getLogin();
		int a=Integer.parseInt(request.getParameter("movieid"));
		Movie m=movieService.findMoviebyId(a);

		Movie mov = new Movie(
				request.getParameter("name"), 
				Integer.parseInt(request.getParameter("year")), 
				request.getParameter("country"), 
				request.getParameter("imagen"));
		mov.setAvg(m.getAvg());
		mov.setId(m.getId());
		mov.setNumberUser(m.getNumberUser());
		
				Movie p = null;
				try {
					p = movieService.updateMovie(mov);
		} catch (OperationUncompletedException e) {
			//incompleto
			System.out.println("enviar a pagina de error con e.getMessage");
		}
		model.addAttribute("movieDetails",p);
//falta enviar a la ficha de la película
		return "movie/editmovie";
	}
		
		
		/*ajax/test*/
		@RequestMapping
		public String list(Model m, HttpServletRequest request, HttpSession session){
			if(!isLogin(session)) return getLogin();
			List<Movie> l;
			try {
				int page = 1;
				if(request.getParameter("page")!=null){
					page = Integer.parseInt(request.getParameter("page"));
					if(page == 0) page++;
				}
				l = movieService.getAllMovies(page, 20, (String)session.getAttribute("username"));
				m.addAttribute("movieList",l);
				m.addAttribute("prev", page - 1);
				m.addAttribute("next", page + 1);
			} catch (OperationUncompletedException e) {
				
			}
			return "movie/list";
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
	
		
		
		@RequestMapping
		public String verwishlist(Model model, HttpSession session){
			if(!isLogin(session)) return getLogin();
			List<Movie> list = null;
			try {
				
				list = movieService.getWishlistbyUsername((String)session.getAttribute("username"));
			
			} catch (OperationUncompletedException e) {
				//incompleto
				System.out.println("enviar a pagina de error con e.getMessage");
			}
			model.addAttribute("movies",list);
			return "/movie/verwishlist";
		}	
	
		@RequestMapping(method = {RequestMethod.POST})
		public String verwishlist(Model m, HttpServletRequest request, HttpSession session){
			if(!isLogin(session)) return getLogin();
			String movie = request.getParameter("wl"); 
			try {
				movieService.deleteMoviefromWishlist( Integer.parseInt(movie), (String)session.getAttribute("username"));
			} catch (OperationUncompletedException e) {
				
			}
		return "/user/verwishlist";	
		
		}
		
		@RequestMapping
		public String addactor(Model m, HttpSession session){
			if(!isLogin(session)) return getLogin();
			User u = (User) session.getAttribute("user");
			if(u.isAdmin()){
			return "movie/addactor";}
			return "redirect:/movie/list";
		}
	
		@RequestMapping(method = {RequestMethod.POST})
		public String addactor(Model m, HttpServletRequest request, HttpSession session){
			if(!isLogin(session)) return getLogin();
			
			Actor act = new Actor(
					request.getParameter("id_name"), 
					request.getParameter("name"), 
					-1);
			
					Actor p = null;
					try {
						p = actorService.addActor(act);
					} catch (OperationUncompletedException e) {
						//incompleto
						System.out.println("enviar a pagina de error");
					}
					if(p != null){
						return "redirect:/movie/list";
					}
			
			return "redirect:/user/bad_confirmation";
		}
		
		@RequestMapping
		public String deletemovie(Model model, HttpSession session){
			if(!isLogin(session)) return getLogin();
			List<Movie> movie = null;
			try {
				movie = movieService.getAllMovies();
			} catch (OperationUncompletedException e) {
				//incompleto
				System.out.println("enviar a pagina de error con e.getMessage");
			}
			model.addAttribute("movies",movie);
			return "/movie/deletemovie";
		}	
		
		@RequestMapping(method = {RequestMethod.POST})
		public String deletemovie(Model model, HttpSession session, HttpServletRequest request) throws OperationUncompletedException{
			if(!isLogin(session)) return getLogin();
			int movieid= Integer.parseInt(request.getParameter("movie"));
			Movie mov = movieService.findMoviebyId(movieid);
			
					List <Movie>p = null;
					try {
						p = movieService.deleteMovie(mov);

					} catch (OperationUncompletedException e) {
						//incompleto
						System.out.println("enviar a pagina de error asdasdsad" + e.toString());
					}
			model.addAttribute("movies", p);
			return "movie/deletemovie2";
		}	
		
}