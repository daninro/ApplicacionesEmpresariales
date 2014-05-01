package screenplay;

import java.sql.Date;



public interface ScreenplayDAO {
		public Screenplay insert(Screenplay sp);
		public Screenplay find(String name, Date date_of_birth);
		public Screenplay delete(Screenplay sp);
		
		public Screenplay deletebykey(String name, Date date);
		
		public Screenplay update(Screenplay sp);
			
}
