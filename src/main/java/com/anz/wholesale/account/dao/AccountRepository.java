package com.anz.wholesale.account.dao;

import com.anz.wholesale.account.entities.Account;
import com.anz.wholesale.account.entities.AccountTransaction;
import com.anz.wholesale.account.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**A Repository for Account entities implemented with Spring Data JPA.
 * @author Dennis Hu
 * @since 2019-10-13
 */
public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {
	Account findAccountById(long id);
}
