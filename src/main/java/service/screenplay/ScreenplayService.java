package service.screenplay;

import java.sql.Date;

import org.springframework.transaction.annotation.Transactional;

import screenplay.Screenplay;
import exceptions.OperationUncompletedException;

public interface ScreenplayService {
	@Transactional
	public Screenplay addScreenplay(Screenplay sp)throws OperationUncompletedException;
	@Transactional
	public Screenplay findScreeplay(String name, Date date_of_birth)throws OperationUncompletedException;
	@Transactional
	public Screenplay deleteScreenplay(Screenplay sp)throws OperationUncompletedException;
	@Transactional
	public Screenplay deleteScreenplaybykey(int id) throws OperationUncompletedException;
	@Transactional
	public Screenplay updateScreenplay(Screenplay sp)throws OperationUncompletedException;
}
