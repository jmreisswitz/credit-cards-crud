package com.jmreisswitz.creditcards.domain.user;


public interface UserRepository {
    User findByLogin(String login);
    void save(User user);
}
