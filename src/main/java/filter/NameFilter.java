package filter;

public class NameFilter extends Filter{
	
		private String name2;
		public NameFilter(String name, String table){
			String nombre ="SELECT N.* " +
							"FROM "+table+" AS N " +
							"WHERE N.name LIKE '%"+name+"%')";
			name2=name;
			super.setFilter(nombre);
		}
		
		public String getQuery(){
			String nombre ="SELECT N.* " +
					"FROM movies AS N " +
					"WHERE N.name LIKE '%"+name2+"%')";
			return nombre;
		}
		
		public String getQuery(String tabla){
			String nombre ="SELECT N.* " +
					"FROM "+tabla+" AS N " +
					"WHERE N.name LIKE '%"+name2+"%')";
			return nombre;
		}
		
}
