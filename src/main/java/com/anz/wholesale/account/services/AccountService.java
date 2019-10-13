package com.anz.wholesale.account.services;

import com.anz.wholesale.account.entities.Account;
import com.anz.wholesale.account.entities.AccountTransaction;
import com.anz.wholesale.account.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * AccountService Interface
 * @author Dennis Hu
 * @since 2019-10-13
 */
@Service
public interface AccountService {

    List<AccountTransaction> getTransactionsByAccountId(long accountNumber);

    List<Account> getAllAccountsOfUser(long userId);

    User findUserById(long userId);

    Account findAccountById(long accountNumber);

}
