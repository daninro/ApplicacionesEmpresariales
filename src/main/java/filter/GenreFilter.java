package filter;

public class GenreFilter extends Filter{

	private String genre2;

			public GenreFilter(String genre, String table){
					String genero ="SELECT G.* " +
									"FROM "+table+" AS G " +
									"WHERE G.id IN (SELECT has.id FROM has WHERE has.name='"+genre+"'))";
					genre2=genre;
					super.setFilter(genero);
			}

			public String getQuery(){
				String genero ="SELECT G.* " +
						"FROM has AS G " +
						"WHERE G.id IN (SELECT has.id FROM has WHERE has.name='"+genre2+"'))";

				return genero;
			}
			
			public String getQuery(String tabla){
				String genero ="SELECT G.* " +
						"FROM "+tabla+" AS G " +
						"WHERE G.id IN (SELECT has.id FROM has WHERE has.name='"+genre2+"'))";
				return genero;
			}
}
