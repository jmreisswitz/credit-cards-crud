package com.jmreisswitz.creditcards.infrastructure.persistence;

import com.jmreisswitz.creditcards.domain.user.User;
import com.jmreisswitz.creditcards.domain.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> users;
    private final PasswordEncoder passwordEncoder;

    public InMemoryUserRepository(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        users = new HashMap<>();
        users.put("username", new User("username", "$2a$10$6lboeNMrylD90Rftp7t.COthxSO1ykGUd5k85Ghti20ZuFJ1awiBa"));
    }

    public User findByLogin(String login) {
        return users.get(login);
    }

    public void save(User user) {
        User encodedUser = new User(user.username(), passwordEncoder.encode(user.password()));
        users.put(user.username(), encodedUser);
    }

}
