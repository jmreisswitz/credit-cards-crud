package com.jmreisswitz.creditcards.infrastructure.persistence;

import com.jmreisswitz.creditcards.domain.user.*;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

    private final Map<Username, User> users;

    public InMemoryUserRepository() {
        users = new HashMap<>();
        User user = new User(
                new Username("username"),
                new UserPassword("$2a$10$6lboeNMrylD90Rftp7t.COthxSO1ykGUd5k85Ghti20ZuFJ1awiBa"));
        save(user);
    }

    public User findBy(Username username) {
        return users.get(username);
    }

    public void save(User user) {
        users.put(user.username(), user);
    }

}
