package SU320878.wave5.bank;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import SU320878.wave5.bank.controller.CusAccTrackerController;
import SU320878.wave5.bank.model.Account;
import SU320878.wave5.bank.model.Customer;
import SU320878.wave5.bank.services.CustomerAccountService;


//@SpringBootTest

@SpringBootTest("CusAccTrackerController.class")

@RunWith(SpringRunner.class)
//@WebMvcTest(CusAccTrackerController.class)
@WebAppConfiguration
class CusAccTrackerControllerTest {
	@Autowired
	WebApplicationContext webApplicationContext;
	MockMvc mockmvc;
	Account account;
	Customer customer;
	
	@BeforeEach
	public void beforeEach() {
		mockmvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
		RequestBuilder request= MockMvcRequestBuilders.post("/customer").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(  new ObjectMapper().writeValueAsString(customer));
	    MvcResult mvcResult= mockmvc.perform(request).andExpect(status().isCreated()).andReturn();
	    Customer customer=gson.fromJson(mvcResult.getResponse().getContentAsString(), Customer.class);
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
		RequestBuilder request= MockMvcRequestBuilders.post("/account").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(  new ObjectMapper().writeValueAsString(account));
	    MvcResult mvcResult= mockmvc.perform(request).andExpect(status().isCreated()).andReturn();
	    Account accountResponse=gson.fromJson(mvcResult.getResponse().getContentAsString(), Account.class);
	    Assert.assertEquals("Verifying account type",account.getAccountType(), accountResponse.getAccountType());
	    Assert.assertEquals("Verifying account Balance",account.getBalance(), accountResponse.getBalance());
	    Assert.assertEquals("Verifying Customer id",account.getCustomerId(), accountResponse.getCustomerId());    
	    this.account=accountResponse;
	}

}
