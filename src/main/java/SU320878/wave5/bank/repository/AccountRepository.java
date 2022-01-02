package SU320878.wave5.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import SU320878.wave5.bank.model.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
	@Query("select ac from Account ac INNER JOIN ac.customer c where c.customerId=:customerId and ac.accountType= :accountType")
	List<Account> getAccountByCustomerId(@Param("customerId")Long customerId,@Param("accountType")String accountType );

	
	
}
