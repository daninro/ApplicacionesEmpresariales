package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import movie.Movie;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.actor.ActorService;
import service.movie.MovieService;
import user.User;
import actor.Actor;
import exceptions.OperationUncompletedException;
import filter.ActorFilter;
import filter.CountryFilter;
import filter.DirectorFilter;
import filter.Filter;
import filter.GenreFilter;
import filter.GroupFilter;
import filter.NameFilter;
import filter.YearFilter;



public class MovieController extends MyController{
	private MovieService movieService;
	private ActorService actorService;
	
	/***services mutators***/
	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}
	
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
			return "movie/addmovie";
		}
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
		mov.setDirector(request.getParameter("director_name"));
		mov.setId_director(request.getParameter("director_id"));
		String[] checkbox= request.getParameterValues("genre");
		Movie p = null;
		try {
			p = movieService.addMovie(mov);
			for(int i = 0; i < checkbox.length; i++)
				movieService.setGenres(checkbox[i], p.getId());
		} catch (OperationUncompletedException e) {
			m.addAttribute("message", e.toString());
		}
		if(p != null){
			return "redirect:/movie/list";
		}
		return "message/message";
	}
	
	/***movie/addmovie getform***/
	@RequestMapping
	public String search(Model m, HttpServletRequest request, HttpSession session){
		if(!isLogin(session)) return getLogin();
		String name = request.getParameter("name");
		if(name == null) 
			return "movie/search";
		List<Movie> l = null;
		int page = 1;
		try {
			if(request.getParameter("page")!=null){
				page = Integer.parseInt(request.getParameter("page"));
				if(page == 0) page++;
			}
			Filter F = new NameFilter(name);
			F.setUsername((String)session.getAttribute("username"));
			l = movieService.FilterMovies(F, page, 20);
			if(movieService.FilterMovies(F, page + 1, 20).isEmpty()){
				m.addAttribute("next", page);	
			}else{
				m.addAttribute("next", page + 1);
			}
		} catch (OperationUncompletedException e) {
			m.addAttribute("message", e.toString());
			return "message/message";
		}
		if(!l.isEmpty()) {
			m.addAttribute("movieList",l);
			m.addAttribute("prev", page - 1);
			m.addAttribute("name", name);
		}
		return "movie/search";
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
	
	@RequestMapping
	public String editmovie(Model model, HttpServletRequest request,  HttpSession session){
		if(!isLogin(session)) return getLogin();
		User u = (User) session.getAttribute("user");
		Movie m= null;
		try {
			int id;
			if(request.getParameter("id") != null)
				id = Integer.parseInt(request.getParameter("id"));
			else return "redirect:index";
			m = movieService.findMoviebyId(id);
		} catch (OperationUncompletedException e) {	
			model.addAttribute("info", "No se pudo encontrar: " + e.getMessage());
			return "message/info";
		}
		model.addAttribute("movieDetails",m);
		if(u.isAdmin()){
			return "movie/editmovie";
		}
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
			
			model.addAttribute("info", "No se pudo agregar: " + e.getMessage());
			return "message/info";
		}
		model.addAttribute("movieDetails",p);
		
		return "redirect: movie/movie?movie="+p.getId();
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
			Filter F = new NameFilter("");
			F.setUsername((String)session.getAttribute("username"));
			l = movieService.FilterMovies(F, page, 20);
			m.addAttribute("movieList",l);
			m.addAttribute("prev", page - 1);
			m.addAttribute("next", page + 1);
		} catch (OperationUncompletedException e) {
			return "redirect:index";
		}
		return "movie/list";
	}
	
	
	/****listo ***/

	@RequestMapping
	public String top20(Model model, HttpSession session){
		if(!isLogin(session)) return getLogin();
		List<Movie> list = null;
		try {
			list = movieService.top20((String)session.getAttribute("username"));
			
		} catch (OperationUncompletedException e) {
			//incompleto
			System.out.println("enviar a pagina de error con e.getMessage");
			return "redirect:index";
		}
		model.addAttribute("top20",list);
		return "/movie/top20";
	}	
	/****listo ***/
	@RequestMapping
	public String last10(Model model, HttpSession session){
		if(!isLogin(session)) return getLogin();
		List<Movie> list = null;
		try {
			list = movieService.last10((String)session.getAttribute("username"));
			System.out.println(list.get(0).getName());
		} catch (OperationUncompletedException e) {
			//incompleto
			System.out.println("enviar a pagina de error con e.getMessage");
			return "redirect:index";
		}
		model.addAttribute("last10",list);
		return "/movie/last10";
	}	
	/**/
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
				System.out.println("enviar a pagina de error con e.getMessage");
				return "redirect:index";
			}
			model.addAttribute("movies",list);
			return "/movie/verwishlist";
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
		
		//listo
		
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
						return "redirect:index";
					}
					if(p != null){
						return "redirect:/movie/list";
					}
			
			return "redirect:/message/message";
		}
		
		@RequestMapping
		public String deletemovie(Model model, HttpSession session){
			if(!isLogin(session)) return getLogin();
			return "/movie/deletemovie";
		}	
		
		@RequestMapping(method = {RequestMethod.POST})
		public String deletemovie(Model model, HttpSession session, HttpServletRequest request){
			if(!isLogin(session)) return getLogin();
			int movieid= Integer.parseInt(request.getParameter("movie"));
					List <Movie>p = null;
					try {
						Movie mov = movieService.findMoviebyId(movieid);
						movieService.deleteMovie(mov);

					} catch (OperationUncompletedException e) {
						//incompleto
						System.out.println("enviar a pagina de error asdasdsad" + e.toString());
						return "redirect:index";
					}
			model.addAttribute("movies", p);
			return "index";
		}
		
		@RequestMapping
		public String movie(Model model, HttpSession session, HttpServletRequest request){
			if(!isLogin(session)) return getLogin();
			int movieid = 0;
			if(request.getParameter("movie") == null) return "index";
			else movieid = Integer.parseInt(request.getParameter("movie"));
			Movie m;
			List<Actor> a;
			try {
				a = actorService.getMoviesPerforms(movieid);
				m = movieService.findMoviebyId(movieid);
				Movie test = movieService.getWandM(movieid, (String)session.getAttribute("username"));
				if(test != null){
					m.setAvg(test.getAvg());
					m.setIsWishlist(test.getIsWishlist());
				}
			} catch (OperationUncompletedException e) {
				return "redirect:index";
			}
			List<String> l = movieService.getGenres(movieid);
			model.addAttribute("genre", l);
			model.addAttribute("movie",m);
			model.addAttribute("actor", a);
			return "movie/movie";
		}
		
		@RequestMapping
		public String executealgorithm(Model model, HttpSession session, HttpServletRequest request) throws OperationUncompletedException{
			if(!isLogin(session)) return getLogin();
			new Thread(new Thread_algorithm()).start();
			return "index";
		}
		
		@RequestMapping
		public String index(Model model, HttpSession session, HttpServletRequest request) throws OperationUncompletedException{
			if(!isLogin(session)) return getLogin();
			new Thread(new Thread_algorithm()).start();
			return "index";
		}
		
		@RequestMapping
		public String filter(Model m, HttpServletRequest request, HttpSession session){
			if(!isLogin(session)) return getLogin();
			String name = request.getParameter("name");

			List<Movie> l = null;
			int page = 1;
			try {
				if(request.getParameter("page")!=null){
					page = Integer.parseInt(request.getParameter("page"));
					if(page == 0) page++;
				}
				GroupFilter F = new GroupFilter();
				if(request.getParameter("genre") != null)
					F.addFilter(new GenreFilter(request.getParameter("genre")));
				if(request.getParameter("year") != null)
					F.addFilter(new YearFilter(request.getParameter("year")));
				if(request.getParameter("country") != null)
					F.addFilter(new CountryFilter(request.getParameter("country")));
				if(request.getParameter("actor") != null)
					F.addFilter(new ActorFilter(request.getParameter("actor")));
				if(request.getParameter("director") != null)
					F.addFilter(new DirectorFilter(request.getParameter("director")));
				if(request.getParameter("name") != null)
					F.addFilter(new NameFilter(request.getParameter("name")));
				
				if(F.size() <= 0) return "movie/search";
				
				F.setUsername((String)session.getAttribute("username"));
				
				l = movieService.FilterMovies(F, page, 20);
				if(movieService.FilterMovies(F, page + 1, 20).isEmpty()){
					m.addAttribute("next", page);	
				}else{
					m.addAttribute("next", page + 1);
				}
			} catch (OperationUncompletedException e) {
				m.addAttribute("message", e.toString());
				return "message/message";
			}
			if(!l.isEmpty()) {
				m.addAttribute("movieList",l);
				m.addAttribute("prev", page - 1);
				m.addAttribute("name", name);
			}
			return "movie/search";
		}
		
		
		
		
		private class Thread_algorithm implements Runnable 
		{
		    public void run() 
		    {
		        movieService.executeAlgorithm();
		        System.out.println("Termino!");
		    }
		    
		}
		
		
}