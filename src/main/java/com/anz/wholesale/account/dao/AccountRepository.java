package com.anz.wholesale.account.dao;

import com.anz.wholesale.account.entities.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

/**A Repository for Account entities implemented with Spring Data JPA.
 * @author Dennis Hu
 * @since 2019-10-13
 */
public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {
	Account findAccountById(long id);
}
