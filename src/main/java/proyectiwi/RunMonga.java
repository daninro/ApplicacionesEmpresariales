package proyectiwi;

import java.util.List;

import movie.Movie;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import services.MovieService;

public class RunMonga {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
		
		
		MovieService ms = (MovieService)context.getBean("movieService");
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

	}

}
