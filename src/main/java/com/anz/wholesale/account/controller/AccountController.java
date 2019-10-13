
package com.anz.wholesale.account.controller;

import com.anz.wholesale.account.entities.Account;
import com.anz.wholesale.account.entities.AccountTransaction;
import com.anz.wholesale.account.entities.User;
import com.anz.wholesale.account.exception.AccountNotFoundException;
import com.anz.wholesale.account.exception.UserNotFoundException;
import com.anz.wholesale.account.services.AccountService;
import com.anz.wholesale.account.services.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * A Spring Web MVC controller offering REST services for accessing user/account/transaction
 * operations of the application.
 */
@RestController
@RequestMapping("/api")
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    /**
     *
     * @param id : id of User entity
     * @return ResponseEntity: contains Accounts belongs to the user.
     */
    @GetMapping({"/users/{id}/accounts"})
    @ResponseBody
    public ResponseEntity<List<Account>> getAccountsByUserId(
            @PathVariable final long id) {

        User user = accountService.findUserById(id);

        if (user == null) {
            throw new UserNotFoundException("User Id " + id + " not exist in the system.");
        }
        logger.info("User info: id -- {}, name -- {}",user.getUserId(), user.getFirstName()+" "+user.getLastName());
        List<Account> returnList = accountService.getAllAccountsOfUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(returnList);
    }


    /**
     *
     * @param id: id of Account entity
     * @return ResponseEntity: contains transactions belongs to the account
     */
    @GetMapping({"/accounts/{id}/transactions"})
    @ResponseBody
    public ResponseEntity<List<AccountTransaction>> getTransactionsByAccountId(
            @PathVariable final long id) {
        Account account = accountService.findAccountById(id);
        if (account == null) {
            throw new AccountNotFoundException("Account Id " + id + " not exist in the system.");
        }
        logger.info("Account info: account number -- {}, name -- {}",account.getAccountNumber(),account.getAccountName());
        List<AccountTransaction> returnList = accountService.getTransactionsByAccountId(id);
        return ResponseEntity.status(HttpStatus.OK).body(returnList);
    }

}
