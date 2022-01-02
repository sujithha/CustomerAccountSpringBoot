package SU320878.wave5.bank.exceptions;


import lombok.Setter;


public class InvalidRequestDataException extends Exception{
	public InvalidRequestDataException(){
		super();
	}
	public InvalidRequestDataException(String message) {
		super(message);
	}
	
}
