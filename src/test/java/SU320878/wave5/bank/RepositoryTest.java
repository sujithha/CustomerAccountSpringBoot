package SU320878.wave5.bank;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import SU320878.wave5.bank.model.Account;
import SU320878.wave5.bank.model.Customer;
import SU320878.wave5.bank.repository.AccountRepository;
import SU320878.wave5.bank.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	AccountRepository accountRepository;
	Account account;
	Customer customer;

	@BeforeEach
	public void beforeEach() {
		customer = new Customer();
		customer.setCustomerName("Sujeeth");
		customer.setContactNo(9901051131l);
		customer.setCustomerId(1l);
		customer.setEmail("sujeeth.a93@wipro.com");

		account = new Account();
		account.setAccountNo(4568l);
		account.setAccountType("CURRENT");
		account.setBalance(2000d);
		account.setCurrency("INR");
		account.setCustomerId(customer.getCustomerId());

	}

	@Test
	void veryfyCustomerRepo() throws Exception {
		List<Customer> customer = new ArrayList<Customer>();
		customer.addAll(customerRepository.findAll());
		Assert.assertEquals("Verifying size of the customer table before save", customer.size(), 0);
		customerRepository.save(this.customer);
		customer.addAll(customerRepository.findAll());
		Assert.assertEquals("Verifying size of the customer table after save", customer.size(), 1);
		Assert.assertEquals("Verifying customer name", this.customer.getCustomerName(),
				customer.get(0).getCustomerName());
		Assert.assertEquals("Verifying customer email", this.customer.getEmail(), customer.get(0).getEmail());
		Assert.assertEquals("Verifying customer contact no", this.customer.getContactNo(),
				customer.get(0).getContactNo());
		List<Customer> customers = customerRepository.getCustomer(this.customer.getCustomerName(),
				this.customer.getContactNo());
		Assert.assertEquals("Verifying size of get customer", customer.size(), 1);
		this.customer = customers.get(0);
		account.setCustomerId(customers.get(0).getCustomerId());

	}

	@Test
	void veryfyAccountRepo() throws Exception {
		Assert.assertEquals("Verifying size of the account table before save", accountRepository.findAll().size(), 0);
		accountRepository.save(account);
		Assert.assertEquals("Verifying size of the account table after save", accountRepository.findAll().size(), 1);
		Assert.assertEquals("Verifying account type", account.getAccountType(),
				accountRepository.findAll().get(0).getAccountType());
		Assert.assertEquals("Verifying account Balance", account.getBalance(),
				accountRepository.findAll().get(0).getBalance());
		Assert.assertEquals("Verifying Customer id", account.getCustomerId(),
				accountRepository.findAll().get(0).getCustomerId());
		}

}
