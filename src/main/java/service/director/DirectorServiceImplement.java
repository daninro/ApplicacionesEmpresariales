package service.director;

import java.sql.Date;

import org.springframework.transaction.annotation.Transactional;

import director.Director;
import director.DirectorDAO;
import exceptions.MyNotFoundException;
import exceptions.OperationUncompletedException;

public class DirectorServiceImplement implements DirectorService{

	private DirectorDAO directorDAO;
	public DirectorDAO getDirectorDAO() {
		return directorDAO;
	}
	public void setDirectorDAO(DirectorDAO directorDAO) {
		this.directorDAO = directorDAO;
	}
	
	@Transactional
	public Director deleteDirector(Director d)throws OperationUncompletedException{
		Director director = null;
		try{
			director = directorDAO.delete(d);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la eliminacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El Director no existe");
			
		}
	return director;
		
	}

	
	@Transactional
	public Director addDirector(Director d)throws OperationUncompletedException{
		Director director = null;
		try{
			director = directorDAO.insert(d);
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema durante la insercion del director");
		} catch (MyNotFoundException e) {
			throw new OperationUncompletedException("No se pudo generar la clave");
		}
		return director;
	}
	public Director findDiretor(String name, Date date_of_birth)throws OperationUncompletedException {
		Director director= null;
		try{
			director = directorDAO.find(name, date_of_birth);
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la busqueda");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El director buscado no existe");
		}
		return director;
	}
	
	@Transactional
	public Director deleteDirectorbykey(int id) throws OperationUncompletedException{
		Director director = null;
		try{
			director = directorDAO.delete(id);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la eliminacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El director no existe");
		}
	return director;
		
	}
	
	@Transactional
	public Director updateDirector(Director d)throws OperationUncompletedException{
		Director director = null;
		try{
			director = directorDAO.update(d);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la actualizacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El director no existe");
		}
	return director;
		
	}
	
}
