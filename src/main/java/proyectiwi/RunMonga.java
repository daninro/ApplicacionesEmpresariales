package proyectiwi;

import java.sql.Date;
import java.util.List;

import movie.Movie;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import person.Actor;
import person.Director;
import person.Screenplay;
import person.User;

import services.MovieService;
import services.UserService;

public class RunMonga {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
		
		
		MovieService ms = (MovieService)context.getBean("movieService");
		UserService us =  (UserService)context.getBean("userService");
		
		Screenplay spp = new Screenplay("danieliwi7", Date.valueOf("2013-02-01"), "chile");
		
		//Screenplay s = ms.addScreenplay(spp);
		//System.out.println(s);
		
		User user = new User("iissfaaisdai32", Date.valueOf("2013-03-01"), "chilafe", "nafataly", "holiaddaii", "taaaeeladgssgidaafi");
		//Screenplay sp = ms.addScreenplay(spp);
	//	User u = us.addUser(user);
		User u= us.deleteUser(user);
		System.out.println(u);
		
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
