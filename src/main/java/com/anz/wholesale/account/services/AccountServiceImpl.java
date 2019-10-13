package com.anz.wholesale.account.services;

import com.anz.wholesale.account.dao.AccountRepository;
import com.anz.wholesale.account.dao.AccountTransactionRepository;
import com.anz.wholesale.account.dao.UserRepository;
import com.anz.wholesale.account.entities.Account;
import com.anz.wholesale.account.entities.AccountTransaction;
import com.anz.wholesale.account.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Account service implementation class.
 * @author Dennis Hu
 * @since 2019-10-13
 */
@Service
public class AccountServiceImpl implements AccountService {

    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Value("${account.pagination.size:100}")
    private int pageSize;


    @Value("${account.pagination.enable:true}")
    private boolean enablePagination;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountTransactionRepository accountTransactionRepository;

    @Autowired
    UserRepository userRepository;


    /**
     *
     * Retrieve transactions of specific account with pagination flag.
     * @param accountId : Id in account entity
     * @return List<AccountTransaction>: Transactions belongs to specific account id.
     */
    @Override
    public List<AccountTransaction> getTransactionsByAccountId(long accountId) {
        if (!enablePagination) {
            logger.info("retrieve transactions without pagination!");
            Account account = accountRepository.findAccountById(accountId);
            return account.getAccountTransactionList();
        } else {
            logger.info("retrieve transactions with pagination!");
            org.springframework.data.domain.Pageable pageRequest = PageRequest.of(0, pageSize);
            Page<AccountTransaction> result = accountTransactionRepository.findByAccountNumber(accountId, pageRequest);
            return result.getContent();
        }
    }


    /**
     *
     * Retrieve all accounts belong to specific user.
     * @param userId : Id in User entity
     * @return List<Account>: Accounts belongs to specific user id.
     */
    @Override
    public List<Account> getAllAccountsOfUser(long userId) {
        User user = userRepository.findUserById(userId);
        return user == null ? null : user.getAccountList();
    }

    /**
     *
     * Retrieve user belong to specific id.
     * @param id : Id in User entity
     * @return User: User belongs to specific id.
     */
    @Override
    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }

    /**
     *
     * Retrieve accounts belong to specific id.
     * @param id : Id in Account entity.
     * @return Account: Account belongs to specific id.
     */
    @Override
    public Account findAccountById(long id) {
        return accountRepository.findAccountById(id);
    }
}
