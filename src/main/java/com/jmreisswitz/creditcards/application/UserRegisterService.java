package com.jmreisswitz.creditcards.application;

import com.jmreisswitz.creditcards.domain.user.*;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(Command command) {
        User user = command.asUser();
        if (userRepository.findBy(user.username()) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }
        var encodedPassword = passwordEncoder.encode(user.password().value());
        user.setPassword(new UserPassword(encodedPassword));
        userRepository.save(user);
    }

    public record Command(String username, String password) {

        public User asUser() {
                return new User(new Username(username), new UserPassword(password));
            }
        }
}
