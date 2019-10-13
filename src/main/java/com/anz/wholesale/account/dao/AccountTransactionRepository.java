package com.anz.wholesale.account.dao;

import com.anz.wholesale.account.entities.AccountTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**A Repository for AccountTransaction entities implemented with Spring Data JPA.
 * @author Dennis Hu
 * @since 2019-10-13
 */
public interface AccountTransactionRepository extends PagingAndSortingRepository<AccountTransaction, Integer> {


	@Query(value = "SELECT * FROM ACCOUNT_TRANSACTION WHERE ACCOUNT_TRANSACTION_ID = ?1  ORDER BY VALUE_DATE  DESC  ",countQuery = "SELECT count(*) FROM ACCOUNT_TRANSACTION WHERE ACCOUNT_TRANSACTION_ID = ?1", nativeQuery = true)
	Page<AccountTransaction> findByAccountNumber(long accountNumber, @Param("pageable")Pageable pageable);
}
