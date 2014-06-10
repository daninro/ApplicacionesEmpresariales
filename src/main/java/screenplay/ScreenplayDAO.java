package screenplay;

import java.sql.Date;
import exceptions.MyNotFoundException;
import exceptions.OperationUncompletedException;

public interface ScreenplayDAO {
		public Screenplay insert(Screenplay sp) throws MyNotFoundException;
		public Screenplay find(String name, Date date_of_birth)throws MyNotFoundException;
		public Screenplay delete(Screenplay sp)throws MyNotFoundException;
		public Screenplay deletebykey(String name, Date date)throws MyNotFoundException;
		public Screenplay update(Screenplay sp)throws MyNotFoundException;
}
