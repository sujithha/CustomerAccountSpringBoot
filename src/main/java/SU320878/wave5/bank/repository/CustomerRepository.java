package SU320878.wave5.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import SU320878.wave5.bank.model.Account;
import SU320878.wave5.bank.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	@Query("select c from Customer c  where c.customerName=:customerName and c.contactNo= :contactNo")
	List<Customer> getCustomer(@Param("customerName")String customerName,@Param("contactNo")Long contactNo );
	
}
