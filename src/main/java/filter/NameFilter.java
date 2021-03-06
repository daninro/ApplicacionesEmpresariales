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
			return getQuery("movie");
		}
		
		public String getQuery(String tabla){
			String nombre ="SELECT N.* " +
					"FROM "+tabla+" AS N " +
					"WHERE UPPER(N.name) LIKE UPPER('%"+name+"%')";
			return nombre;
		}
		
		public String getQueryByUser(String User){
			System.out.println(User);
			System.out.println(super.getQueryByUser(User));
			return getQuery("("+super.getQueryByUser(User)+")");
		}

}
