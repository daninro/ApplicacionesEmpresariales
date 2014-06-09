package exceptions;

public class OperationUncompletedException extends Exception{
	private static final long serialVersionUID = 1L;
	private String message;
	private int counter = 1;
	public OperationUncompletedException(String message){	
		this.message = message;
	}
	
	@Override
	public String toString(){
		return "message: " + message;
	}
	
	public void addDetails(String s){
		this.message = message + '\n' + "detail "+ (counter++) + ": " + s;
	}
	
	@Override
	public String getMessage(){
		return toString();
	}
	
}
