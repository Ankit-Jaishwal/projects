package exception.com.thoughtworks;

public class InvalidInputException extends Exception{
	private String message = null;
	public InvalidInputException(String message){
		this.message = message;
	}
	public InvalidInputException(){
		super();
	}
	public String getMessage(){
		return message;	
	}
}