package com.anz.wholesale.account.services;

import com.anz.wholesale.account.dao.AccountRepository;
import com.anz.wholesale.account.dao.AccountTransactionRepository;
import com.anz.wholesale.account.dao.UserRepository;
import com.anz.wholesale.account.entities.Account;
import com.anz.wholesale.account.entities.AccountTransaction;
import com.anz.wholesale.account.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {


    @Mock
    AccountRepository accountRepository;

    @Mock
    AccountTransactionRepository accountTransactionRepository;

    @Mock
    UserRepository userRepository;


    @InjectMocks
    private AccountServiceImpl accountService;

    private List<Account> accounts;

    private List<AccountTransaction> accountTransactions;

    private User user;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
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
    }


    @Test
    public void getTransactionsByAccountId_WithPagination() {
        ReflectionTestUtils.setField(accountService, "enablePagination", true);
        ReflectionTestUtils.setField(accountService, "pageSize", 2);
        Page<AccountTransaction> tt = new PageImpl(accountTransactions);
        when(accountTransactionRepository.findByAccountNumber(Mockito.anyLong(), isA(Pageable.class)))
                .thenReturn(tt);
        List<AccountTransaction> result = accountService.getTransactionsByAccountId(338l);
        assertThat(result, is(tt.getContent()));
    }


    @Test
    public void getTransactionsByAccountId_WithoutPagination() {
        ReflectionTestUtils.setField(accountService, "enablePagination", false);
        ReflectionTestUtils.setField(accountService, "pageSize", 2);
        when( accountRepository.findAccountById(anyLong())).thenReturn(accounts.get(0));
        List<AccountTransaction> result = accountService.getTransactionsByAccountId(338l);
        assertThat(result, is(accounts.get(0).getAccountTransactionList()));
    }






}