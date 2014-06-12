package filter;

import java.util.ArrayList;
import java.util.List;

public class GroupFliter extends Filter{
	private List<Filter> filters;
	
	public GroupFliter(){
		filters = new ArrayList<Filter>();
	}
	
	public void addFilter(Filter f){
		filters.add(f);
	}
	public String getFilter(){
		String toRet = "";
		for(int i = 0; i < filters.size() - 1; i++){
			toRet += filters.get(i).getFilter() + " AND ";
		}
		if(!filters.isEmpty())
			toRet +=  filters.get(filters.size() - 1).getFilter();
		return toRet;
	}
}
