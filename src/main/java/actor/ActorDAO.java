package actor;

import java.util.List;

import exceptions.MyNotFoundException;

public interface ActorDAO {

	
	public Actor insert(Actor a) throws MyNotFoundException;

	public Actor find(String id)throws MyNotFoundException;
	
	public Actor delete(Actor a)throws MyNotFoundException;
	
	public Actor delete(String id)throws MyNotFoundException;
	
	public Actor update(Actor a) throws MyNotFoundException;
	
	public List<Actor> getActorsOfMovie(int id) throws MyNotFoundException;
		
}
