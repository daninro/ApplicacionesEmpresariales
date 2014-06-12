package filter;

public class GenreFilter extends Filter{

		public GenreFilter(String genre){
			super.setFilter("genre = '" + genre + "'");
		}
}
