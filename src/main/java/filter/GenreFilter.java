package filter;

public class GenreFilter extends Filter{

	private String genre;

		public GenreFilter(String genre, String table){
				this.genre = genre;
				super.setQuery(getQuery(table));
		}
		public GenreFilter(String genre){
			this.genre = genre;
			super.setQuery(getQuery());
		}

		public String getQuery(){
			return getQuery("movie");
		}
		
		@Override
		public String getQuery(String table){
			String genero ="SELECT G.* " +
					"FROM "+table+" AS G " +
					"WHERE G.id IN (SELECT has.id FROM has WHERE has.name='"+genre+"')";
			return genero;
		}
		
		public String getQueryByUser(String User){
			return getQuery("("+super.getQueryByUser(User)+")");
		}

}
