package filter;

public class ActorFilter extends Filter{

		private String actor2;
		public ActorFilter(String actor, String table){
			String act="SELECT A.* " +
							"FROM "+table+" AS A " +
							"WHERE A.id IN (SELECT perform.id FROM perform WHERE id_actor='"+actor+"')"; 
			actor2=actor;
			super.setFilter(act);
		}
		
		public String getQuery(){
			String act="SELECT A.* " +
					"FROM perform AS A " +
					"WHERE A.id IN (SELECT perform.id FROM perform WHERE id_actor='"+actor2+"')"; 
			return act;
		}
		
		public String getQuery(String tabla){
			String act="SELECT A.* " +
					"FROM "+tabla+" AS A " +
					"WHERE A.id IN (SELECT perform.id FROM perform WHERE id_actor='"+actor2+"')"; 
			return act;
		}
}
