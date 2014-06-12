package filter;

public class CountryFilter extends Filter{
	
	public CountryFilter(String country){
		super.setFilter("year = '" + country + "'");
	}
}
