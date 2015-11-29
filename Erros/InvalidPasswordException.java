package Erros;

@SuppressWarnings("serial")
public class InvalidPasswordException extends Exception{
	
	public InvalidPasswordException (String message){
		super(message);
	}

}
