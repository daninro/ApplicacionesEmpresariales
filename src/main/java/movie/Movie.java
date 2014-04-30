package movie;

public class Movie {
	
	private int id;
	private String name;
	private int year;
	private int running_time;
	private String country;
	private int budget;
	private int box_office;
	
	public Movie( String name, int year, int running_time, String country, int budget, int box_office){
		this.id = -1;
		this.setName(name);
		this.setYear(year);
		this.setRunning_time(running_time);
		this.setCountry(country);
		this.setBudget(budget);
		this.setBox_office(box_office);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getRunning_time() {
		return running_time;
	}

	public void setRunning_time(int running_time) {
		this.running_time = running_time;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getBox_office() {
		return box_office;
	}

	public void setBox_office(int box_office) {
		this.box_office = box_office;
	}
	
	@Override
	public String toString(){
		return "Movie [name = " + name + " " + id + "]";
	}
}
