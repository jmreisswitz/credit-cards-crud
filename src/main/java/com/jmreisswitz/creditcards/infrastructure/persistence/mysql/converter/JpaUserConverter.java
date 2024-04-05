package com.jmreisswitz.creditcards.infrastructure.persistence.mysql.converter;

import com.jmreisswitz.creditcards.domain.user.User;
import com.jmreisswitz.creditcards.domain.user.UserId;
import com.jmreisswitz.creditcards.domain.user.UserPassword;
import com.jmreisswitz.creditcards.domain.user.Username;
import com.jmreisswitz.creditcards.infrastructure.persistence.mysql.entity.JpaUser;

import java.time.Instant;
import java.util.Date;

public class JpaUserConverter {

    private JpaUserConverter() {
        // static class
    }

    public static JpaUser toMySqlUser(User user) {
        Integer userIdAsInteger = user.id() == null ? null : user.id().value();
        return new JpaUser(userIdAsInteger, user.username().value(),
                user.password().value(), Date.from(Instant.now()));
    }

    public static User toUser(JpaUser jpaUser) {
        if (jpaUser == null) {
            return null;
        }
        return new User(new UserId(jpaUser.getId()),
                new Username(jpaUser.getUsername()),
                new UserPassword(jpaUser.getPassword()));
    }

}
