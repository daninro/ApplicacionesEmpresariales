package testing;

import java.io.ObjectInputStream.GetField;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import movie.JdbcMovieDAO;
import movie.Movie;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import actor.Actor;

import exceptions.OperationUncompletedException;
import filter.ActorFilter;
import filter.CountryFilter;
import filter.DirectorFilter;
import filter.Filter;
import filter.GenreFilter;
import filter.GroupFilter;
import filter.NameFilter;


import service.actor.ActorService;
import service.movie.MovieService;
import service.movie.MovieServiceImplement;
import service.user.UserService;
import service.user.UserServiceImplement;
import user.User;

public class Run {

	/**
	 * @param args
	 * @throws OperationUncompletedException 
	 */
	public static void main(String[] args) throws OperationUncompletedException {
		// 
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
		
		MovieService ms = (MovieService)context.getBean("movieService");
		UserService us = (UserService)context.getBean("userService"); 
		Movie movie = new Movie("lola", 1997, "EEUU", "imagen");
		System.out.println(movie);
		movie = ms.addMovie(movie);
		System.out.println(movie);
		ActorService as = (ActorService)context.getBean("actorService");
		
		
		
		
		//List<Actor> a = as.getMoviesPerforms(1);
		
		
		//for(int i = 0; i < a.size(); i++)
		//	System.out.println(a.get(i));
		
		ArrayList<Filter> F = new ArrayList<Filter>();
		F.add(new GenreFilter("Adventure"));
		F.add(new GenreFilter("Animation"));
		F.add(new GenreFilter("Children"));
		F.add(new GenreFilter("Comedy"));
		//F.add(new GenreFilter("Fantasy"));
		F.add(new CountryFilter("USA"));
		//F.add(new NameFilter("Toy"));
		//F.add(new ActorFilter("annie_potts"));
		//F.add(new DirectorFilter("john_lasseter"));
		
		GroupFilter F4 = new GroupFilter();
		for (int i = 0; i < F.size(); i++) {
			F4.addFilter(F.get(i));
		}
		
		List<Movie> m = ms.FilterMovies(F4);
		for(int i = 0; i < m.size(); i++)
			System.out.println(m.get(i));
		
		//List<Movie> l = ms.getAllMovies();
		
		
		
		
		/*
		try{
			l = ms.deleteMovie(ms.findMoviebyId(12));
		}
		catch(OperationUncompletedException e){
			System.out.println(e.toString());
			
		}
		
		for(int i = 0; i < l.size(); i++){
			System.out.println(l.get(i).toString());
		}
		
		User u = us.addUser(new User("Diego Seco", Date.valueOf("1982-10-31"), "espanol", "dseco@udec.cl","pass", "dsecos", true));
		//System.out.println(u.toString());
		*/
		
		
	}

}
