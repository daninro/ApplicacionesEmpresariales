package filter;

public abstract class Filter {
	private String query;
	private String toUser;
	private String username;
	
	public Filter(){
		toUser = "SELECT DISTINCT movie.*, evaluate2.calification, evaluate2.iswished " +
				"FROM movie LEFT JOIN evaluate2 " +
				"ON movie.id = evaluate2.id AND evaluate2.user_name = '";
	}
	public abstract String getQuery(String query);
	
	public String getQueryByUser(String User){
		return toUser + User + "'";
	} 
	
	
	public String getQuery(){
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
		
}
