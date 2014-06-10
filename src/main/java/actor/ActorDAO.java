package actor;

import java.sql.Date;
import exceptions.MyNotFoundException;

public interface ActorDAO {

	
	public Actor insert(Actor a) throws MyNotFoundException;

	public Actor find(String name, Date date_of_birth)throws MyNotFoundException;
	
	public Actor find(int id)throws MyNotFoundException;
	
	public Actor delete(Actor a)throws MyNotFoundException;
	
	public Actor delete(int id)throws MyNotFoundException;
	
	public Actor update(Actor a) throws MyNotFoundException;
		
}
