package actor;

import java.sql.Date;

import controllers.MyController;

import director.Director;

import exceptions.MyNotFoundException;
import exceptions.OperationUncompletedException;

public interface ActorDAO {

	
	public Actor insert(Actor a);

	public Actor find(String name, Date date_of_birth)throws MyNotFoundException;
	
	public Actor delete(Actor a)throws MyNotFoundException;
	
	public Actor deletebykey(String name, Date date)throws MyNotFoundException;
	
	public Actor update(Actor a) throws MyNotFoundException;
		
}
