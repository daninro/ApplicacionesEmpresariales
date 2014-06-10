package director;

import java.sql.Date;

import exceptions.MyNotFoundException;


public interface DirectorDAO {

	public Director insert(Director d)throws MyNotFoundException;
	public Director find(String name, Date date_of_birth)throws MyNotFoundException;
	public Director delete(Director d)throws MyNotFoundException;
	
	public Director deletebykey(String name, Date date) throws MyNotFoundException;
	
	public Director update(Director d)throws MyNotFoundException;
	
}
