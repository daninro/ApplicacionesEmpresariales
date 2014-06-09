package testing;

import movie.Movie;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import exceptions.OperationUncompletedException;

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

		try {
			Movie l = ms.findMoviebyId(1000);
		} catch (OperationUncompletedException e) {
			System.out.println(e.toString());
		}

	}

}
