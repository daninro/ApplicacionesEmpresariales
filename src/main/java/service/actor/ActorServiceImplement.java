package service.actor;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import actor.Actor;
import actor.ActorDAO;
import exceptions.MyNotFoundException;
import exceptions.OperationUncompletedException;

public class ActorServiceImplement implements ActorService{
	private ActorDAO actorDAO;
	
	public ActorDAO getActorDAO() {
		return actorDAO;
	}
	public void setActorDAO(ActorDAO actorDAO) {
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
	
	public Actor findActor(String name)throws OperationUncompletedException {
		Actor actor = null;
		try{
			actor = actorDAO.find(name);
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
	public Actor deleteActorbykey(String id) throws OperationUncompletedException{
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
	@Override
	@Transactional
	public List<Actor> getMoviesPerforms(int id)throws OperationUncompletedException {
		List<Actor> actors = null;
		try{
			actors = actorDAO.getActorsOfMovie(id);
			
		}catch(RuntimeException e){
			throw new OperationUncompletedException("Ocurrio un problema");
		}catch (MyNotFoundException e) {
			throw new OperationUncompletedException("no hay actores");
			}
	return actors;
	}
	

}
