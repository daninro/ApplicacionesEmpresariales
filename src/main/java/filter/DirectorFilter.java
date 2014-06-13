package filter;

public class DirectorFilter extends Filter{

		private String director2;
		public DirectorFilter(String director, String table){
			
			String direct="SELECT D.* " +
							"FROM "+table+" AS D " +
							"WHERE D.id_director='"+director+"')"; 
			director2=director;
			super.setFilter(direct);
		}
		
		public String getQuery(){
			String direct="SELECT D.* " +
					"FROM movies AS D " +
					"WHERE D.id_director='"+director2+"')"; 
			return direct;
		}
		
		public String getQuery(String tabla){
			String direct="SELECT D.* " +
					"FROM "+tabla+" AS D " +
					"WHERE D.id_director='"+director2+"')"; 
			return direct;
		}
}
