package service.actor;

import java.util.List;

import actor.Actor;
import exceptions.OperationUncompletedException;

public interface ActorService {
	
	public Actor addActor(Actor a)throws OperationUncompletedException;
	public Actor findActor(String name)throws OperationUncompletedException;
	public Actor deleteActor(Actor a) throws OperationUncompletedException;
	public Actor deleteActorbykey(String id) throws OperationUncompletedException;
	public Actor updateActor(Actor a) throws OperationUncompletedException;
	public List<Actor> getMoviesPerforms(int id) throws OperationUncompletedException;
	
}
