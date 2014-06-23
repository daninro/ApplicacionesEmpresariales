package filter;

public class DirectorFilter extends Filter{

		private String director;
		
		public DirectorFilter(String director, String table){ 
			this.director=director;
			super.setQuery(getQuery(table));
		}
		
		public DirectorFilter(String director){ 
			this.director=director;
			super.setQuery(getQuery());
		}
		@Override
		public String getQuery(){
			 
			return getQuery("movies");
		}
		@Override
		public String getQuery(String tabla){
			String direct="SELECT D.* " +
					"FROM "+tabla+" AS D " +
					"WHERE D.id_director='"+director+"'"; 
			return direct;
		}
		
		
}
