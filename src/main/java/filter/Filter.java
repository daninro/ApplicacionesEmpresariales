package filter;

public abstract class Filter {
	private String query;
	
	public Filter(){
	}
	
	public abstract String getQuery(String query);
	public String getQuery(){
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
		
}
