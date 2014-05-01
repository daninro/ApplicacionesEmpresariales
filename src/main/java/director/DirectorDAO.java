package director;

import java.sql.Date;


public interface DirectorDAO {

	public Director insert(Director d);
	public Director find(String name, Date date_of_birth);
	public Director delete(Director d);
	
	public Director deletebykey(String name, Date date);
	
	public Director update(Director d);
	
}
