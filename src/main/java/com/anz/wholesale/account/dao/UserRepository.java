package com.anz.wholesale.account.dao;

import com.anz.wholesale.account.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**A Repository for User entities implemented with Spring Data JPA.
 * @author Dennis Hu
 * @since 2019-10-13
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserById(long userId);
}
