package actor;

import java.sql.Date;

public interface ActorDAO {

	
	public Actor insert(Actor a);

	public Actor find(String name, Date date_of_birth);
		
}
