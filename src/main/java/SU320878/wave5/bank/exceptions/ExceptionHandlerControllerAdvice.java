package SU320878.wave5.bank.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	@ExceptionHandler (value=ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException exception,final HttpServletRequest request) {
	ExceptionResponse response= new ExceptionResponse();
	response.setErrorMessage(exception.getMessage());
	response.setRequestedUri(request.getRequestURI());	
	return response;
	}
	
	@ExceptionHandler (value=InvalidRequestDataException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse handleInvalidRequestBody(InvalidRequestDataException exception,final HttpServletRequest request) {
	ExceptionResponse response= new ExceptionResponse();
	response.setErrorMessage(exception.getMessage());
	response.setRequestedUri(request.getRequestURI());	
	return response;
	}
	
	@ExceptionHandler (value=InsufficientFundsException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody ExceptionResponse handleInsufficientFunds(InsufficientFundsException exception,final HttpServletRequest request) {
	ExceptionResponse response= new ExceptionResponse();
	response.setErrorMessage(exception.getMessage());
	response.setRequestedUri(request.getRequestURI());	
	return response;
	}

}
