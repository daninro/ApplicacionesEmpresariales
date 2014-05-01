package director;

import java.sql.Date;

public interface DirectorDAO {

	public Director insert(Director d);
	public Director find(String name, Date date_of_birth);
		
	
}
