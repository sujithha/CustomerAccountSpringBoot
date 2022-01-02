package SU320878.wave5.bank.exceptions;


import lombok.Setter;


public class ResourceNotFoundException extends Exception{
	public ResourceNotFoundException(){
		super();
	}
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
