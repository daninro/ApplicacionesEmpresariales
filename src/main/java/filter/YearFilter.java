package filter;

public class YearFilter extends Filter{

	private String year;
	public YearFilter(String year, String table){
			this.year=year;
			super.setQuery(getQuery(table));
	}

	public YearFilter(String year){
		this.year=year;
		super.setQuery(getQuery());
	}
	
	
	public String getQuery(String tabla){
		String ano ="SELECT Y.* " +
				"FROM "+tabla+" AS Y " +
				"WHERE Y.year = '"+year+"'";
		return ano;
	}
	
	public String getQuery(){
		return getQuery("movie");
	}
	
	
	
}
