package testing;

import java.util.List;

import movie.Movie;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.movie.MovieServiceImplement;
import service.user.UserServiceImplement;

public class RunDanieliwi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
		MovieServiceImplement ms = (MovieServiceImplement)context.getBean("movieService");
		UserServiceImplement us= (UserServiceImplement)context.getBean("userService");
				//ms.findMoviebyId(4);
		/*MovieService ms = (MovieService)context.getBean("movieService");
		Movie movie = new Movie("kill bill", 2003, 240, "EEUU", 5000000, 20000000);
		System.out.println(movie);
		movie = ms.addMovie(movie);
		System.out.println(movie);*/
		//List <Movie> l = ms.findMoviebyYear(1997);
		//User u1= new User('daniel','1234', 'dani','' ,'' ,'' );
		//User u = us.addUser(u1)
	//asdsad	
		Movie l = ms.findMoviebyId(3);
	//	ms.deleteMovie(ms.findMoviebyId(4));
		/*for(int i = 0; i < l.size(); i++){
			System.out.println(l.get(i).toString());
		}*/
		//System.out.println(u.toString());
	}

}
