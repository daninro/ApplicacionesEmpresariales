package actor;

public class Actor{
	private int ranking;
	private String id_name;
	private String name;
	
	public Actor(String name, String id_name, int ranking) {
		this.id_name = id_name;
		this.name = name;
		this.ranking = ranking;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public String getId_name() {
		return id_name;
	}
	public void setId_name(String id_name) {
		this.id_name = id_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
