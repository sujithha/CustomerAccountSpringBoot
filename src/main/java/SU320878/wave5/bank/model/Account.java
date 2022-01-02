package SU320878.wave5.bank.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType; 
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Account") @Setter @Getter
public class Account {
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull @Column private Long accountNo;
	@NotNull @Column private Double balance;
	@NotNull @Column private String currency;
	@NotNull @Column private String accountType;
	@ManyToOne(
			cascade =  CascadeType.PERSIST,fetch = FetchType.LAZY
	  )
	@JoinColumn(name = "customerId", referencedColumnName = "customerId")
	@JsonBackReference
	private Customer customer;
	
	@Column(name = "customerId",insertable = false, updatable = false) private Long customerId;
	
}
