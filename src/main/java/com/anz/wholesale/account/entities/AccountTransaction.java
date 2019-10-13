package com.anz.wholesale.account.entities;

import com.anz.wholesale.account.type.AccountType;
import com.anz.wholesale.account.type.CurrencyType;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;


/**
 * The persistent class for the account transaction database table.
 * @author Dennis Hu
 * @since 2019-10-13
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(unique = true, nullable = false)
	private long  id;

	@ManyToOne
	@JoinColumn(name="ACCOUNT_TRANSACTION_ID")
	private Account account;

	@Column(unique = true, nullable = false)
	private long  transactionNumber;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@Temporal(TemporalType.DATE)
	private Date valueDate;

	@Enumerated(EnumType.STRING)
	private CurrencyType transactionCurrencyType;

	private BigDecimal debitAmount;

	private BigDecimal creditAmount;

	private String transactionNarrative;
	private boolean isDebit(){
		return  debitAmount!=null && debitAmount.compareTo(BigDecimal.ZERO) > 0 ;
	}

	@UpdateTimestamp
	@Column(name = "transactionTime", insertable = false)
	private OffsetDateTime transactionTime;

	public AccountTransaction(long transactionNumber) {
		this.transactionCurrencyType = transactionCurrencyType;
	}
}