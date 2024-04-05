package com.jmreisswitz.creditcards.infrastructure.persistence.mysql;

import com.jmreisswitz.creditcards.domain.user.User;
import com.jmreisswitz.creditcards.domain.user.UserRepository;
import com.jmreisswitz.creditcards.domain.user.Username;
import com.jmreisswitz.creditcards.infrastructure.persistence.mysql.converter.JpaUserConverter;
import com.jmreisswitz.creditcards.infrastructure.persistence.mysql.jparepository.JpaUserRepository;

public class JpaAdaptedUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public JpaAdaptedUserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User findBy(Username username) {
        var jpaUser = jpaUserRepository.findByUsername(username.value());
        return JpaUserConverter.toUser(jpaUser);
    }

    @Override
    public void save(User user) {
        jpaUserRepository.save(JpaUserConverter.toMySqlUser(user));
    }
}
