package service.actor;

import java.sql.Date;

import actor.Actor;
import exceptions.OperationUncompletedException;

public interface ActorService {
	
	public Actor addActor(Actor a)throws OperationUncompletedException;
	public Actor findActor(String name, Date date_of_birth)throws OperationUncompletedException;
	public Actor deleteActor(Actor a) throws OperationUncompletedException;
	public Actor deleteActorbykey(int id) throws OperationUncompletedException;
	public Actor updateActor(Actor a) throws OperationUncompletedException;
	
}
