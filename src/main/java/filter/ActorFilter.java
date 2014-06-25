package filter;

public class ActorFilter extends Filter{

		private String actor;
		public ActorFilter(String actor, String table){
			this.actor=actor;
			super.setQuery(getQuery(table));
		}
		public ActorFilter(String actor){
			this.actor=actor;
			super.setQuery(getQuery());
		}
		@Override
		public String getQuery(){
			return getQuery("movie");
		}
		@Override
		public String getQuery(String tabla){
			String act="SELECT A.* " +
					"FROM "+tabla+" AS A " +
					"WHERE A.id IN (SELECT perform.id FROM perform WHERE id_actor='"+actor+"')"; 
			return act;
		}
		
		public String getQueryByUser(String User){
			return getQuery("("+super.getQueryByUser(User)+")");
		}
}
