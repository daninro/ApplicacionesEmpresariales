package filter;

public class CountryFilter extends Filter{
	
	private String country2;
	public CountryFilter(String country, String table){
			String pais ="SELECT C.* " +
							"FROM "+table+" AS C " +
							"WHERE C.country = '"+country+"')";
			country2=country;
			super.setFilter(pais);
	}

	public String getQuery(){
		String pais ="SELECT C.* " +
				"FROM movies AS C " +
				"WHERE C.country = '"+country2+"')";
		return pais;
	}
	
	public String getQuery(String tabla){
		String pais ="SELECT C.* " +
				"FROM "+tabla+" AS C " +
				"WHERE C.country = '"+country2+"')";
		return pais;
	}
}
