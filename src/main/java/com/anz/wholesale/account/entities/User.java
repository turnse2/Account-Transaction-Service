package com.anz.wholesale.account.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
/**
 * The persistent class for the User database table.
 * @author Dennis Hu
 * @since 2019-10-13
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "USER_ID", unique = true, nullable = false)
    private Long userId;
    private String username;
    @Column(name = "FIRST_NAME", unique = true, nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", unique = true, nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accountList;

    public User(long userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
