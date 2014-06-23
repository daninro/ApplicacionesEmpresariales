package filter;

import java.util.ArrayList;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<Filter> F = new ArrayList<Filter>();
		
		F.add(new GenreFilter("Adventure"));
		F.add(new GenreFilter("Animation"));
		F.add(new GenreFilter("Children"));
		F.add(new GenreFilter("Comedy"));
		F.add(new GenreFilter("Fantasy"));
		F.add(new CountryFilter("USA"));
		F.add(new NameFilter("Toy"));
		F.add(new ActorFilter("annie_potts"));
		F.add(new DirectorFilter("john_lasseter"));
		System.out.println(F.get(6).getQuery());
		GroupFilter F4 = new GroupFilter();
		
		for(int i = 0; i < F.size(); i++)
			F4.addFilter(F.get(i));
		System.out.println(F4.getQuery());
	}

}
