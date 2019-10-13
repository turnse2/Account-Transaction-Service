package com.anz.wholesale.account.dao;

import com.anz.wholesale.account.entities.AccountTransaction;
import com.anz.wholesale.account.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountTransactionRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Before
    public void setup() {

    }

    @Test
    public void whenfindByAccountNumber_thenReturnEmployee() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<AccountTransaction> found = accountTransactionRepository.findByAccountNumber(1l, pageable);
        assertNotNull(found.getContent());
        assertThat(found.getSize(), is(2));
    }

}