package com.jmreisswitz.creditcards.domain.user;

import java.util.Objects;

public class User {

    private UserId id;
    private Username username;
    private UserPassword password;

    public User(UserId id, Username username, UserPassword password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Username username, UserPassword password) {
        this.username = username;
        this.password = password;
    }

    public void setId(UserId id) {
        this.id = id;
    }

    public UserId id() {
        return id;
    }

    public Username username() {
        return username;
    }

    public UserPassword password() {
        return password;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !getClass().equals(other.getClass())) {
            return false;
        }
        return equalsCasted((User) other);
    }

    private boolean equalsCasted (User other){
        return Objects.equals(username, other.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
