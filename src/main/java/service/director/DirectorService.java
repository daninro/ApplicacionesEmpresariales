package service.director;

import java.sql.Date;

import director.Director;
import exceptions.OperationUncompletedException;

public interface DirectorService {

	public Director deleteDirector(Director d)throws OperationUncompletedException;
	public Director addDirector(Director d)throws OperationUncompletedException;
	public Director findDiretor(String name, Date date_of_birth)throws OperationUncompletedException;
	public Director deleteDirectorbykey(int id) throws OperationUncompletedException;
	public Director updateDirector(Director d)throws OperationUncompletedException;
}
