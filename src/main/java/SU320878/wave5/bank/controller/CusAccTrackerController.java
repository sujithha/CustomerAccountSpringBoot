package SU320878.wave5.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import SU320878.wave5.bank.exceptions.InsufficientFundsException;
import SU320878.wave5.bank.exceptions.InvalidRequestDataException;
import SU320878.wave5.bank.exceptions.ResourceNotFoundException;
import SU320878.wave5.bank.model.Account;
import SU320878.wave5.bank.model.Customer;
import SU320878.wave5.bank.services.CustomerAccountService;

@RestController
public class CusAccTrackerController {
	@Autowired
	CustomerAccountService customerAccountService;
	@PostMapping("/account")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) throws ResourceNotFoundException, InvalidRequestDataException{
		Account createdAccount=customerAccountService.addAccount(account);
		return new ResponseEntity<Account>(createdAccount,HttpStatus.CREATED);
	}
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws ResourceNotFoundException, InvalidRequestDataException{
		Customer createdCustomer=customerAccountService.addCustomer(customer);
		return new ResponseEntity<Customer>(createdCustomer,HttpStatus.CREATED);
	}
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customers= customerAccountService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
		
	}
	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> getAllAccounts(){
		List<Account> customers= customerAccountService.getAllAccounts();
		return new ResponseEntity<List<Account>>(customers,HttpStatus.OK);
	}
	@PutMapping("/transfer/fromAc/{fromAcc}/toAc/{toAcc}/amount/{amount}")
	public ResponseEntity<String> tranferMoney(@PathVariable("fromAcc") Long fromAcc, @PathVariable("toAcc") Long toAcc,@PathVariable("amount") Double amount) throws InvalidRequestDataException, InsufficientFundsException{
		String response= customerAccountService.transferFunds(fromAcc, toAcc, amount);
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	@GetMapping("/balance/{accountNo}")
	public ResponseEntity<Double> getBalance(@PathVariable("accountNo")Long accountNo) throws InvalidRequestDataException{
		Double balance= customerAccountService.getBalanceOf(accountNo);
		return new ResponseEntity<Double>(balance,HttpStatus.OK);
		
	}
	@DeleteMapping("/account/{accountNo}")
	public ResponseEntity<String> deleteAccount(@PathVariable("accountNo")Long accountNo) throws InvalidRequestDataException, ResourceNotFoundException{
		customerAccountService.deleteAccount(accountNo);
		return new ResponseEntity<String>("Succesfully deleted account "+accountNo,HttpStatus.OK);
		
	}
	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customerId")Long customerId) throws InvalidRequestDataException, ResourceNotFoundException{
		customerAccountService.deleteCustomer(customerId);
		return new ResponseEntity<String>("Succesfully deleted customer "+customerId,HttpStatus.OK);
		
	}
	@PutMapping("/account")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account) throws ResourceNotFoundException, InvalidRequestDataException{
		Account createdAccount=customerAccountService.updateAccount(account);
		return new ResponseEntity<Account>(createdAccount,HttpStatus.CREATED);
	}
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws ResourceNotFoundException, InvalidRequestDataException{
		Customer createdCustomer=customerAccountService.updateCustomer(customer);
		return new ResponseEntity<Customer>(createdCustomer,HttpStatus.CREATED);
	}
}
