package com.jmreisswitz.creditcards.application;

import com.jmreisswitz.creditcards.domain.User;
import com.jmreisswitz.creditcards.domain.UserAlreadyExistsException;
import com.jmreisswitz.creditcards.domain.UserRepository;

public class RegisterService {

    private final UserRepository userRepository;

    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(Command command) {
        User user = new User(command.getUsername(), command.getPassword());
        if (userRepository.findByLogin(user.username()) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }
        userRepository.save(user);
    }

    public static final class Command {
        private final String username;
        private final String password;

        public Command(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
