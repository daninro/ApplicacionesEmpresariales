package filter;

public class YearFilter extends Filter{

	public YearFilter(int year) {
		super.setFilter("year = '" + year + "'");
	}

	
}
