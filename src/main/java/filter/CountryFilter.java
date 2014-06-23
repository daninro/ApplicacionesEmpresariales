package filter;


public class CountryFilter extends Filter{
	
	private String country;
	public CountryFilter(String country, String table){
			this.country=country;
			super.setQuery(getQuery(table));
	}
	
	public CountryFilter(String country){
		this.country=country;
		super.setQuery(getQuery());
	}
	
	public String getQuery(){
		return getQuery("movie");
	}
	
	@Override
	public String getQuery(String table){
		String query ="SELECT C.* " +
				"FROM "+table+" AS C " +
				"WHERE C.country = '"+country+"'";
		return query;
	}
	
}
