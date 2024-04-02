package com.jmreisswitz.creditcards.domain;


public interface UserRepository {
    User findByLogin(String login);
    void save(User user);
}
