package com.jmreisswitz.creditcards.domain.user;

public class User {

    private Username username;
    private UserPassword password;

    public User(Username username, UserPassword password) {
        this.username = username;
        this.password = password;
    }

    public Username username() {
        return username;
    }

    public UserPassword password() {
        return password;
    }

}
