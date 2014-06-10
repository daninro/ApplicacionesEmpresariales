package service.screenplay;

import java.sql.Date;

import org.springframework.transaction.annotation.Transactional;

import screenplay.JdbcScreenplayDAO;
import screenplay.Screenplay;
import screenplay.ScreenplayDAO;
import exceptions.MyNotFoundException;
import exceptions.OperationUncompletedException;

public class ScreenplayServiceImplement implements ScreenplayService{
	
	private ScreenplayDAO screenplayDAO;

	public ScreenplayDAO getScreenplayDAO() {
		return screenplayDAO;
	}
	public void setScreenplayDAO(JdbcScreenplayDAO screenplayDAO) {
		this.screenplayDAO = screenplayDAO;
	}

	
	@Transactional
	public Screenplay addScreenplay(Screenplay sp)throws OperationUncompletedException{
		Screenplay s = null;
		try{
			s = screenplayDAO.insert(sp);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema durante la insercion del guionista");
		} catch (MyNotFoundException e) {
			throw new OperationUncompletedException("No se pudo generar la clave");		}
		return s;
	}
	
	
	
	public Screenplay findScreeplay(String name, Date date_of_birth) throws OperationUncompletedException{
		Screenplay sp = null;
		try{
			sp = screenplayDAO.find(name, date_of_birth);
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la busqueda");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El guionista buscado no existe");
		}
		return sp;
	}

			

	@Transactional
	public Screenplay deleteScreenplay(Screenplay sp)throws OperationUncompletedException{
		Screenplay screenplay = null;
		try{
			screenplay = screenplayDAO.delete(sp);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la eliminacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El guionista no existe");
		}
	return screenplay;
		
	}
	
	@Transactional
	public Screenplay deleteScreenplaybykey(int id)throws OperationUncompletedException{
		
		Screenplay screenplay = null;
		try{
			screenplay = screenplayDAO.delete(id);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la eliminacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El guionista no existe");
		}
	return screenplay;
		
	}
	
	@Transactional
	public Screenplay updateScreenplay(Screenplay sp)throws OperationUncompletedException{
		
		Screenplay screenplay = null;
		try{
			screenplay = screenplayDAO.update(sp);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la actualizacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El guionista no existe");
		}
	return screenplay;
		
	}


}
