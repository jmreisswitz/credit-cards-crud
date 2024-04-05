package com.jmreisswitz.creditcards.infrastructure.persistence.mysql;

import com.jmreisswitz.creditcards.domain.user.User;
import com.jmreisswitz.creditcards.domain.user.UserRepository;
import com.jmreisswitz.creditcards.domain.user.Username;
import com.jmreisswitz.creditcards.infrastructure.persistence.mysql.converter.MySqlUserConverter;

public class JpaAdaptedUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public JpaAdaptedUserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User findBy(Username username) {
        var jpaUser = jpaUserRepository.findByUsername(username.value());
        return MySqlUserConverter.toUser(jpaUser);
    }

    @Override
    public void save(User user) {
        jpaUserRepository.save(MySqlUserConverter.toMySqlUser(user));
    }
}
