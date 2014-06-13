package filter;

public class YearFilter extends Filter{

	private String year2;
	public YearFilter(String year, String table){
			String ano ="SELECT Y.* " +
							"FROM "+table+" AS Y " +
							"WHERE C.year = '"+year+"')";
			year2=year;
			super.setFilter(ano);
	}

	public String getQuery(){
		String ano ="SELECT Y.* " +
				"FROM movies AS Y " +
				"WHERE C.year = '"+year2+"')";
		return ano;
	}
	
	public String getQuery(String tabla){
		String ano ="SELECT Y.* " +
				"FROM "+tabla+" AS Y " +
				"WHERE C.year = '"+year2+"')";
		return ano;
	}
	
}
