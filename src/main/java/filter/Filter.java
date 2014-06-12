package filter;

public abstract class Filter {
	private String query;
	
	public Filter(){
		
	}
	
	public void setFilter(String query){
		this.query = query;
	}
	
	public String getFilter(){
		return query;
	}
}
