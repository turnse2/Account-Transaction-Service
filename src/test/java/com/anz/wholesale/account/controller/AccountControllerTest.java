package com.anz.wholesale.account.controller;

import com.anz.wholesale.account.entities.Account;
import com.anz.wholesale.account.entities.AccountTransaction;
import com.anz.wholesale.account.entities.User;
import com.anz.wholesale.account.exception.AccountNotFoundException;
import com.anz.wholesale.account.exception.UserNotFoundException;
import com.anz.wholesale.account.services.AccountService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private List<Account> accounts;

    private List<AccountTransaction> accountTransactions;

    private User user;

    @Before
    public void setup(){
         Account account1= new Account(111, "account1");
        Account account2= new Account(112, "account2");
        Account account3= new Account(113, "account3");
        accounts = Arrays.asList(account1,account2,account3);
        AccountTransaction trx1 = new AccountTransaction(90001);
        AccountTransaction trx2 = new AccountTransaction(90002);
        AccountTransaction trx3 = new AccountTransaction(90003);
        accountTransactions = Arrays.asList(trx1,trx2,trx3);
        account1.setAccountTransactionList(accountTransactions);
        user = new User(1001l, "first_user");
        user.setId(2L);
    }


    @Test
    public void getAccountsByUserId_whenUserNotExist_ThenReturnUserNotFoundException() throws Exception {

        when(accountService.getAllAccountsOfUser(Mockito.anyLong()))
                .thenReturn(new ArrayList<Account>());

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/users/7788/accounts").
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        Optional<UserNotFoundException> someException = Optional.ofNullable((UserNotFoundException) result.getResolvedException());
        someException.ifPresent( (se) -> assertThat(se, is(notNullValue())));
        someException.ifPresent( (se) -> assertThat(se.getMessage(), is("User Id 7788 not exist in the system.")));
        someException.ifPresent( (se) -> assertThat(se, is(instanceOf(UserNotFoundException.class))));
    }

    @Test
    public void getTransactionsByAccountNumber_whenAccountNotExist_ThenReturnAccountNotFoundException() throws Exception {
        when(accountService.getTransactionsByAccountId(Mockito.anyLong()))
                .thenReturn(new ArrayList<AccountTransaction>());

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/accounts/251/transactions").
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        Optional<AccountNotFoundException> someException = Optional.ofNullable((AccountNotFoundException) result.getResolvedException());
        someException.ifPresent( (se) -> assertThat(se, is(notNullValue())));
        someException.ifPresent( (se) -> assertThat(se.getMessage(), is("Account Id 251 not exist in the system.")));
        someException.ifPresent( (se) -> assertThat(se, is(instanceOf(AccountNotFoundException.class))));
    }

    @Test
    public void getAccountsByUserId_whenUserExist_ThenReturnAllAccountsOfUserUser() throws Exception {
        when(accountService.findUserById(Mockito.anyLong()))
                .thenReturn(user);
        when(accountService.getAllAccountsOfUser(Mockito.anyLong()))
                .thenReturn(accounts);
         mvc.perform(MockMvcRequestBuilders.get("/api/users/1/accounts").
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getTransactionsByAccountNumber_whenAccountExist_ThenReturnAllTransactions() throws Exception {
        when(accountService.findAccountById(Mockito.anyLong()))
                .thenReturn(accounts.get(0));
        when(accountService.getTransactionsByAccountId(Mockito.anyLong()))
                .thenReturn(accountTransactions);
        mvc.perform(MockMvcRequestBuilders.get("/api/accounts/1/transactions").
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }





}