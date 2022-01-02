package SU320878.wave5.bank.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="customer") @Setter @Getter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column private Long customerId;
	
	@NotNull @Column() private String customerName;
	@NotNull @Column() private Long contactNo;
	@NotNull @Column() private String email;
	@OneToMany(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "customer")
	
	@JsonManagedReference
	private Set<Account> account;

}
