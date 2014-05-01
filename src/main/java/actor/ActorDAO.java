package actor;

import java.sql.Date;

public interface ActorDAO {

	
	public Actor insert(Actor a);

	public Actor find(String name, Date date_of_birth);
	
	public Actor delete(Actor a);
	
	public Actor deletebykey(String name, Date date);
	
	public Actor update(Actor a);
		
}
