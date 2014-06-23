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

	public String getQuery(){
		String toRet = "";
		if(filters.size() > 0)
			toRet += filters.get(0).getQuery();
		if(filters.size() > 1)
			for (int i = 1; i < filters.size();i++){
				toRet = filters.get(i).getQuery("(" + toRet + ")");
			}
		return toRet;
	}
	
	@Override
	public String getQuery(String query) {
		return getQuery();
	}
	
	
}
