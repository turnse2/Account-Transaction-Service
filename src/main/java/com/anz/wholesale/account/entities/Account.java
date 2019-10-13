package com.anz.wholesale.account.entities;

import com.anz.wholesale.account.type.AccountType;
import com.anz.wholesale.account.type.CurrencyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the registration database table.
 * @author Dennis Hu
 * @since 2019-10-13
 */
@Entity
@Table(name = "account")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(unique = true, nullable = false)
	private long  id;


	@Column(name = "ACCOUNT_NUMBER", unique = true, nullable = false)
	private long  accountNumber;

	@Column( nullable = false, length = 200)
	private String accountName;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@Temporal(TemporalType.DATE)
	private Date balanceDate;

	@Enumerated(EnumType.STRING)
	private CurrencyType accountCurrencyType;

	private BigDecimal openingAvailableBalance;

	@UpdateTimestamp
	private java.time.OffsetDateTime updatedTime;


	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<AccountTransaction> accountTransactionList;

	@ManyToOne
	@JoinColumn(name = "user_account_id")
	@JsonIgnore
	private User user;


	public Account(long accountNumber,String accountName) {
		this.accountNumber =accountNumber;
		this.accountName = accountName;
	}
}