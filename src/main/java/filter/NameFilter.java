package filter;

public class NameFilter extends Filter{
	
		private String name;
		public NameFilter(String name, String table){
			this.name=name;
			this.setQuery(getQuery(table));
		}
		
		public NameFilter(String name){
			this.name=name;
			this.setQuery(getQuery());
		}

		public String getQuery(){
			return getQuery("movies");
		}
		
		public String getQuery(String tabla){
			String nombre ="SELECT N.* " +
					"FROM "+tabla+" AS N " +
					"WHERE N.name LIKE '%"+name+"%'";
			return nombre;
		}
		
}
