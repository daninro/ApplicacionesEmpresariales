package movie;

public class Movie {
	
	private int id;
	private String name;
	private int year;
	private String country;
	private int avg;
	private boolean isWishlist;
	private int number_user;
	private String image;
	
	public Movie(String name, int year, String country, String image){
		this.id = -1;
		this.setName(name);
		this.setYear(year);
		this.setCountry(country);
		this.setImage(image);
			
	}

	public void setImage(String image){
		this.image=image;
	}
	
	public String getImage(){
		return image;
	}
	
	public void setNumberUser(int numberuser){
		this.number_user=numberuser;
	}
	public int getNumberUser(){
		return number_user;
	}
	
	
	public boolean isWishlist(){
		return isWishlist;
	}
	
	public void isWishlist(boolean isWishlist){
		this.isWishlist=isWishlist;
	}
	
	public int getAvg(){
		return avg;
	}
	
	public void setAvg(int avg){
		this.avg=avg;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString(){
		return "Movie [name = " + name + " " + id + "]";
	}
}
