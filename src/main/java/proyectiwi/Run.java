package proyectiwi;

import java.io.ObjectInputStream.GetField;
import java.sql.Date;
import java.util.List;

import movie.JdbcMovieDAO;
import movie.Movie;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import person.User;

import services.MovieService;
import services.UserService;

public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
		
		
		/*
		JdbcMovieDAO m = (JdbcMovieDAO) context.getBean("movieDao");
		
		
		
		Movie movie = new Movie("titanic2", 1997, 120, "EEUU", 2000000, 30000000);
		System.out.println(movie.toString());
		m.insert(movie);
		System.out.println(movie.toString());
		*/
		
		MovieService ms = (MovieService)context.getBean("movieService");
		UserService us = (UserService)context.getBean("userService"); 
		Movie movie = new Movie("titanic3", 1997, 120, "EEUU", 2000000, 30000000);
		System.out.println(movie);
		movie = ms.addMovie(movie);
		System.out.println(movie);
		List<Movie> l = ms.getAllMovies();
		ms.deleteMovie(ms.findMoviebyId(4));
		for(int i = 0; i < l.size(); i++){
			System.out.println(l.get(i).toString());
			
		}
		
		User u = us.addUser(new User("hola", Date.valueOf("2013-02-13"), "chile", "asd","asd", "asds"));
		
		//User u = us.getUserbyUsername("hola");
		System.out.println(u.toString());
		
		
		
	}

}
