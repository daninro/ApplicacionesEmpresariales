package testing;

import java.io.ObjectInputStream.GetField;
import java.sql.Date;
import java.util.List;

import movie.JdbcMovieDAO;
import movie.Movie;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import exceptions.OperationUncompletedException;


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
		
		List<Movie> l = ms.getAllMovies();
		
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
		
		
		
	}

}
