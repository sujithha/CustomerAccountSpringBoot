package SU320878.wave5.bank;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import SU320878.wave5.bank.model.Account;
import SU320878.wave5.bank.model.Customer;
import SU320878.wave5.bank.repository.AccountRepository;
import SU320878.wave5.bank.repository.CustomerRepository;
import SU320878.wave5.bank.services.CustomerAccountServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
	@InjectMocks
	CustomerAccountServiceImpl customerAccountServiceImpl;
	@Mock
	AccountRepository accountRepository;
	@Mock
	CustomerRepository customerRepository;
	
	Account account;
	Customer customer;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		customer=new Customer();
		customer.setCustomerName("Sujeeth");
		customer.setContactNo(9901051131l);
		customer.setCustomerId(1l);
		customer.setEmail("sujeeth.a93@wipro.com");
		
		account= new Account();
		account.setAccountNo(4568l);
		account.setAccountType("CURRENT");
		account.setBalance(2000d);
		account.setCurrency("INR");
		account.setCustomerId(customer.getCustomerId());
		
	
	}
	@Test
	void createCustomerTest() throws Exception {
		List<Customer> returnList=new ArrayList<Customer>();
		returnList.add(this.customer);
	    when(customerRepository.getCustomer(customer.getCustomerName(), customer.getContactNo())).thenReturn(new ArrayList<Customer>(),returnList);
	    when(customerRepository.save(customer)).thenReturn(customer);
	    Customer customer=customerAccountServiceImpl.addCustomer(this.customer)   ;
		 Assert.assertEquals("Verifying customer name",this.customer.getCustomerName(), customer.getCustomerName());
		 Assert.assertEquals("Verifying customer email",this.customer.getEmail(), customer.getEmail());
		 Assert.assertEquals("Verifying customer contact no",this.customer.getContactNo(), customer.getContactNo());    
	    verify(customerRepository,times(2)).getCustomer(customer.getCustomerName(), customer.getContactNo());
	    verify(customerRepository,times(1)).save(this.customer);
		  
	}
	
	@Test
	void createAccount() throws Exception {
		List<Account> returnList=new ArrayList<Account>();
		returnList.add(this.account);
		Optional<Customer> optCust= Optional.of(this.customer);
		
	    when(customerRepository.findById(account.getCustomerId())).thenReturn(optCust);
	    when(accountRepository.getAccountByCustomerId(account.getCustomerId(),
				account.getAccountType())).thenReturn(new ArrayList<Account>(),returnList);
	    when(accountRepository.save(account)).thenReturn(this.account);
	
	    Account accountResponse=customerAccountServiceImpl.addAccount(this.account);
	    Assert.assertEquals("Verifying account type",account.getAccountType(), accountResponse.getAccountType());
	    Assert.assertEquals("Verifying account Balance",account.getBalance(), accountResponse.getBalance());
	    Assert.assertEquals("Verifying Customer id",account.getCustomerId(), accountResponse.getCustomerId());    
	       verify(customerRepository,times(1)).findById(account.getCustomerId());
	    verify(accountRepository,times(2)).getAccountByCustomerId(account.getCustomerId(),
				account.getAccountType());
	    verify(accountRepository,times(1)).save(account);
		  
	}
	
	
}
