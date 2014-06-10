package testing;

import java.sql.Date;
import java.util.List;

import movie.Movie;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import director.Director;

import actor.Actor;


import screenplay.Screenplay;
import service.movie.MovieServiceImplement;
import service.user.UserServiceImplement;
import user.User;

public class RunMonga {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
		
		
		MovieServiceImplement ms = (MovieServiceImplement)context.getBean("movieService");
		UserServiceImplement us =  (UserServiceImplement)context.getBean("userService");
		
		//Screenplay spp = new Screenplay("danieliwi7", Date.valueOf("2013-02-01"), "chile");
		
		//Screenplay s = ms.addScreenplay(spp);
		//System.out.println(s);
		
		User user = new User("nn", Date.valueOf("2013-03-01"), "chilafe", "nafataly", "holiaddaii", "lala", false);
		
		//Screenplay sp = ms.addScreenplay(spp);
		//Screenplay a = new Screenplay("danieliwi7", 	Date.valueOf("2013-02-01"),"chileeeeee");
	//User u = us.addUser(user);
		//us.editUser(user);
		us.deleteUser(user);
		//User u= us.deleteUser(user);
		//ms.addScreenplay(a);
		//System.out.println(ms.updateScreenplay(a));
		//ms.deleteScreenplay(a);
	//	System.out.println(u);
		
		
		/*
		
		Movie movie = new Movie("titanic2000", 2000, 120, "peru", 2000000, 30000000);
		//System.out.println(movie);
		movie = ms.addMovie(movie);
		
		//System.out.println(movie.getId());
		List<Movie> l = ms.findMoviebyCountry("peru");
		List<Movie> ll = ms.findMoviebyCountry("EEUU");
		
		for(int i = 0; i < l.size(); i++){
			System.out.println("peru"+l.get(i).toString());
			
		}
		
		for(int i = 0; i < ll.size(); i++){
			System.out.println("eeuu"+ll.get(i).toString());
			
		}
		
		l =ms.deleteMovie(movie);
		for(int i = 0; i < l.size(); i++){
			System.out.println("rem"+l.get(i).toString());
			
		}
*/
	}

}
