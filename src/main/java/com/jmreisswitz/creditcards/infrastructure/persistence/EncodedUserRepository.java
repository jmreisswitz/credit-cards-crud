package com.jmreisswitz.creditcards.infrastructure.persistence;

import com.jmreisswitz.creditcards.domain.user.User;
import com.jmreisswitz.creditcards.domain.user.UserPassword;
import com.jmreisswitz.creditcards.domain.user.UserRepository;
import com.jmreisswitz.creditcards.domain.user.Username;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodedUserRepository implements UserRepository {

    private final UserRepository delegate;
    private final PasswordEncoder passwordEncoder;

    public EncodedUserRepository(UserRepository delegate, PasswordEncoder passwordEncoder) {
        this.delegate = delegate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findBy(Username username) {
        return delegate.findBy(username);
    }

    @Override
    public void save(User user) {
        String encodedPassword = passwordEncoder.encode(user.password().value());
        User encodedUser = new User(user.username(), new UserPassword(encodedPassword));
        delegate.save(encodedUser);
    }
}
