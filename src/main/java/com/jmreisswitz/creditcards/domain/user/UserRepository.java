package com.jmreisswitz.creditcards.domain.user;


public interface UserRepository {
    User findBy(Username username);
    void save(User user);
}
