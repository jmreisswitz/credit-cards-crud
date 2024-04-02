package com.jmreisswitz.creditcards.infrastructure.controller;

import com.jmreisswitz.creditcards.infrastructure.controller.request.LoginRequest;
import com.jmreisswitz.creditcards.infrastructure.controller.response.LoginResponse;
import com.jmreisswitz.creditcards.infrastructure.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        User principal = (User) auth.getPrincipal();
        var token = jwtUtils.generateToken(principal.getUsername());

        return ResponseEntity.ok(new LoginResponse(token));
    }

}
