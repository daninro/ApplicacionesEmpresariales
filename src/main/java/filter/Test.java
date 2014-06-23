package filter;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Filter[] F = new Filter[7];
		F[0] = new GenreFilter("Adventure");
		F[1] = new GenreFilter("Animation");
		F[2] = new GenreFilter("Children");
		F[3] = new GenreFilter("Comedy");
		F[4] = new GenreFilter("Fantasy");
		F[5] = new CountryFilter("USA");
		F[6] = new YearFilter("2007");
		GroupFliter F4 = new GroupFliter();
		
		for(int i = 0; i < F.length; i++)
			F4.addFilter(F[i]);
		System.out.println(F4.getQuery());
	}

}
