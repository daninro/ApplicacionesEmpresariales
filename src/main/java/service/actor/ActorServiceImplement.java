package service.actor;

import java.sql.Date;

import org.springframework.transaction.annotation.Transactional;

import actor.Actor;
import actor.ActorDAO;
import actor.JdbcActorDAO;
import exceptions.MyNotFoundException;
import exceptions.OperationUncompletedException;

public class ActorServiceImplement {
	private ActorDAO actorDAO;
	
	public ActorDAO getActorDAO() {
		return actorDAO;
	}
	public void setActorDAO(JdbcActorDAO actorDAO) {
		this.actorDAO = actorDAO;
	}

	
	@Transactional
	public Actor addActor(Actor a)throws OperationUncompletedException{
		Actor actor = null;
		try{
			actor = actorDAO.insert(a);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema durante la insercion dela ctor");
		} catch (MyNotFoundException e) {
			throw new OperationUncompletedException("Ocurrio un problema durante la insercion dela ctor");
		}
		return actor;
		
	}
	
	public Actor findActor(String name, Date date_of_birth)throws OperationUncompletedException {
		Actor actor = null;
		try{
			actor = actorDAO.find(name, date_of_birth);
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la busqueda");
		}
		catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El actor buscado no existe");
		}
			
		return actor;
	}
	
	@Transactional
		
	public Actor deleteActor(Actor a) throws OperationUncompletedException{
		Actor actor = null;
		try{
			actor = actorDAO.delete(a);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la eliminacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El actor no existe");
		}
		
	return actor;
		
	}

	@Transactional
	public Actor deleteActorbykey(int id) throws OperationUncompletedException{
		Actor actor = null;
		try{
			actor = actorDAO.delete(id);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la eliminacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El actor no existe");}
	return actor;
		
	}

	@Transactional
	public Actor updateActor(Actor a) throws OperationUncompletedException {
		Actor actor = null;
		try{
			actor = actorDAO.update(a);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema en la actualizacion");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("El actor no existe");}
	return actor;
		
	}

}
