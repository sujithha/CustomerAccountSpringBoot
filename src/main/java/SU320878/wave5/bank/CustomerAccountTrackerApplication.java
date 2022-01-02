package SU320878.wave5.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class CustomerAccountTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAccountTrackerApplication.class, args);
	}

}
