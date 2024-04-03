package com.jmreisswitz.creditcards.application;

import com.jmreisswitz.creditcards.domain.user.*;

public class RegisterService {

    private final UserRepository userRepository;

    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(Command command) {
        User user = command.asUser();
        if (userRepository.findBy(user.username()) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }
        userRepository.save(user);
    }

    public record Command(String username, String password) {

        public User asUser() {
                return new User(new Username(username), new UserPassword(password));
            }
        }
}
