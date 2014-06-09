package exceptions;

public class MyNotFoundExeption extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;
	private int counter = 1;
	public MyNotFoundExeption(String message){
		this.message = message;
	}
	
	@Override
	public String toString(){
		return this.getMessage() + "message: " + message;
	}
	public void addDetails(String s){
		this.message = message + "detail "+ (counter++) + ": " + s;
	}
	
	
}
