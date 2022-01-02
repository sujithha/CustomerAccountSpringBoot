package SU320878.wave5.bank.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SU320878.wave5.bank.exceptions.InsufficientFundsException;
import SU320878.wave5.bank.exceptions.InvalidRequestDataException;
import SU320878.wave5.bank.exceptions.ResourceNotFoundException;
import SU320878.wave5.bank.model.Account;
import SU320878.wave5.bank.model.Customer;
import SU320878.wave5.bank.repository.AccountRepository;
import SU320878.wave5.bank.repository.CustomerRepository;

@Service("CustomerAccountService")
public class CustomerAccountServiceImpl implements CustomerAccountService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Account addAccount(Account account) throws ResourceNotFoundException, InvalidRequestDataException {
		customerRepository.findById(account.getCustomerId()).orElseThrow(()->new ResourceNotFoundException(
				"Customer with customer id " + account.getCustomerId() + " does not exist"));
			List<Account> accounts = accountRepository.getAccountByCustomerId(account.getCustomerId(),
					account.getAccountType());
			if (accounts.size() == 0) {
				Customer customer= new Customer();
				customer.setCustomerId(account.getCustomerId());
				account.setCustomer(customer);
				accountRepository.save(account);
				accounts = accountRepository.getAccountByCustomerId(account.getCustomerId(),
						account.getAccountType());
				return accounts.get(0);
			} else {
				throw new InvalidRequestDataException(
						"The account of type " + account.getAccountType() + " Already exists for customer");
			}
		
	}

	@Override
	public Customer addCustomer(Customer customer) throws InvalidRequestDataException {
		if(customerRepository.getCustomer(customer.getCustomerName(), customer.getContactNo()).size()==0) {
		customerRepository.save(customer);
		return customerRepository.getCustomer(customer.getCustomerName(), customer.getContactNo()).get(0);
		}
		else {
			throw new InvalidRequestDataException(
					"Customer "+customer.getCustomerName()+" already exists please add different customer");
		}
		
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public String transferFunds(Long from, Long to, double amount) throws InvalidRequestDataException, InsufficientFundsException {
		Account fromAc=accountRepository.findById(from).orElseThrow(()->new InvalidRequestDataException(
				"The provided account No "+from+" does not exist"));
		Account toAc=accountRepository.findById(to).orElseThrow(()->new InvalidRequestDataException(
				"The provided account No "+to+" does not exist"));
		if(fromAc.getBalance()>=amount) {
		 Double fromAcBalance=fromAc.getBalance()-amount;
		 Double toAcBalance=toAc.getBalance()+amount;
		 fromAc.setBalance(fromAcBalance);
		 toAc.setBalance(toAcBalance);
		 accountRepository.save(fromAc);
		 accountRepository.save(fromAc);
		 return "{\"status\":\"success\",\n"
		 		+ "\"Balance\":\n{\""+from+"\":"+fromAcBalance+",\n\""+to+"\":"+toAcBalance+"}"
		 		+ "}";
			 
		 }
	
		else {
			throw new InsufficientFundsException("Account "+from+"  does not have sufficient balance to transfer funds more than "+fromAc.getBalance());
		}
		
		
	}

	@Override
	public Double getBalanceOf(Long accountNumber) throws InvalidRequestDataException {
		// TODO Auto-generated method stub
	 accountRepository.findById(accountNumber).orElseThrow(()->new InvalidRequestDataException(
				"The provided account No does not exist"));
	 return  accountRepository.findById(accountNumber).get().getBalance();
	}

	@Override
	public void deleteCustomer(Long customerId) throws ResourceNotFoundException {
		customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Provided customer does not exist"));
		customerRepository.deleteById(customerId);
		
	}

	@Override
	public void deleteAccount(Long accountNo) throws ResourceNotFoundException {
		accountRepository.findById(accountNo).orElseThrow(()->new ResourceNotFoundException("Provided account does not exist"));
		accountRepository.deleteById(accountNo);
		
	}

	@Override
	public Account updateAccount(Account account) throws ResourceNotFoundException {
		accountRepository.findById(account.getAccountNo()).orElseThrow(()->new ResourceNotFoundException("Provided account does not exist"));
		Customer customer=new Customer();
		customer.setCustomerId(account.getCustomerId());
		accountRepository.save(account);
		return accountRepository.getOne(account.getAccountNo());
	}

	@Override
	public Customer updateCustomer(Customer customer) throws ResourceNotFoundException {
	customerRepository.findById(customer.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Provided customer does not exist"));
	Set<Account> accounts=customerRepository.getOne(customer.getCustomerId()).getAccount();
	customer.setAccount(accounts);
	customerRepository.save(customer);
	return customerRepository.getOne(customer.getCustomerId());
	}

}
