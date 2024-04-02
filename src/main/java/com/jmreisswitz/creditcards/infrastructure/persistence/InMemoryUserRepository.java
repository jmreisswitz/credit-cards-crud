package com.jmreisswitz.creditcards.infrastructure.persistence;

import com.jmreisswitz.creditcards.domain.User;
import com.jmreisswitz.creditcards.domain.UserRepository;

import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> users;

    public InMemoryUserRepository() {
        users = Map.of(
            "username", new User("username", "$2a$10$6lboeNMrylD90Rftp7t.COthxSO1ykGUd5k85Ghti20ZuFJ1awiBa")
        );
    }

    public User findByLogin(String login) {
        return users.get(login);
    }

}
