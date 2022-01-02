package SU320878.wave5.bank.exceptions;


import lombok.Setter;


public class InsufficientFundsException extends Exception{
	public InsufficientFundsException(){
		super();
	}
	public InsufficientFundsException(String message) {
		super(message);
	}
	
}
