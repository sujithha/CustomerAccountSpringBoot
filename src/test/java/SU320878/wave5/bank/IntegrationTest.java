package SU320878.wave5.bank;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import SU320878.wave5.bank.model.Account;
import SU320878.wave5.bank.model.Customer;
@SpringBootTest("CusAccTrackerController.class")

@RunWith(SpringRunner.class)

public class IntegrationTest {
	
    private TestRestTemplate restTemplate;
	private String host="http://localhost:8086";
	Account account;
	Customer customer;
	
	@BeforeEach
	public void beforeEach() {
		restTemplate= new TestRestTemplate();
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
		Gson gson= new GsonBuilder().create(); 
		String s=gson.toJson(this.customer);
		System.out.println("Request:"+s);
	    ResponseEntity<Customer>	response=restTemplate.postForEntity(host+"/customer", customer, Customer.class);
		Customer customer=response.getBody();
		Assert.assertEquals("Verifying the status code",response.getStatusCode(), HttpStatus.CREATED);
	    Assert.assertEquals("Verifying customer name",this.customer.getCustomerName(), customer.getCustomerName());
	    Assert.assertEquals("Verifying customer email",this.customer.getEmail(), customer.getEmail());
	    Assert.assertEquals("Verifying customer contact no",this.customer.getContactNo(), customer.getContactNo());    
	    this.customer=customer;
	}
	@Test
	void createAccountTest() throws Exception {
		Gson gson= new GsonBuilder().create(); 
		String s=gson.toJson(account);
		System.out.println("Request:"+s);
		ResponseEntity<Account>	response=restTemplate.postForEntity(host+"/account", account, Account.class);
		Account accountResponse=response.getBody();
		Assert.assertEquals("Verifying the status code",response.getStatusCode(), HttpStatus.CREATED);
    	Assert.assertEquals("Verifying account type",account.getAccountType(), accountResponse.getAccountType());
	    Assert.assertEquals("Verifying account Balance",account.getBalance(), accountResponse.getBalance());
	    Assert.assertEquals("Verifying Customer id",account.getCustomerId(), accountResponse.getCustomerId());    
	    this.account=accountResponse;
	}
}
