package SU320878.wave5.bank.services;

import java.util.List;

import org.springframework.stereotype.Service;

import SU320878.wave5.bank.exceptions.InsufficientFundsException;
import SU320878.wave5.bank.exceptions.InvalidRequestDataException;
import SU320878.wave5.bank.exceptions.ResourceNotFoundException;
import SU320878.wave5.bank.model.Account;
import SU320878.wave5.bank.model.Customer;
@Service
public interface CustomerAccountService {
	public Account addAccount(Account acc) throws ResourceNotFoundException, InvalidRequestDataException;
	public Customer addCustomer(Customer customer) throws  InvalidRequestDataException;
	public List<Account> getAllAccounts();
	public List<Customer> getAllCustomers();
	public String transferFunds(Long from,Long
			to,double amount) throws InvalidRequestDataException, InsufficientFundsException;
	public Double getBalanceOf(Long accountNumber) throws InvalidRequestDataException;
    public void deleteCustomer(Long customerId) throws ResourceNotFoundException;
    public void deleteAccount(Long accountNo) throws ResourceNotFoundException;
    public Account updateAccount(Account account) throws ResourceNotFoundException;
    public Customer updateCustomer(Customer customer) throws ResourceNotFoundException;
    
}
