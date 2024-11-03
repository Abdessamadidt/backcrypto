package xtrebot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xtrebot.entities.User;
import xtrebot.requests.LoginRequest;
import xtrebot.requests.RegisterRequest;
import xtrebot.services.UserService;
import xtrebot.utils.JwtTokenUtil;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Endpoint de connexion
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean isValidUser = userService.validateUserCredentials(loginRequest.getEmail(), loginRequest.getPassword());
        if (isValidUser) {
            // Retrieve user and check if it exists
            User user = userService.getUserByEmail(loginRequest.getEmail());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "User not found"));
            }

            // Ensure all properties used are non-null
            String prenom = user.getPrenom() != null ? user.getPrenom() : "";
            String nom = user.getNom() != null ? user.getNom() : "";
            String username = user.getUsername() != null ? user.getUsername() : "";
            String email = user.getEmail() != null ? user.getEmail() : "";
            double usdtSolde = 0;
            double btcSolde = 0;

            // Generate token
            String token = JwtTokenUtil.generateToken(username, email, prenom, nom, usdtSolde, btcSolde);

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "message", "Login successful",
                    "email", email,
                    "prenom", prenom,
                    "nom", nom,
                    "username", username,
                    "usdtSolde", usdtSolde
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid email or password"));
        }
    }


    // Endpoint d'inscription
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        // Check if the username already exists
        if (userService.userExists(registerRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Username already taken"));
        }

        // Check if the email already exists
        if (userService.emailExists(registerRequest.getEmail())) { // Ensure you have this method in your UserService
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Email already exists"));
        }

        // Create a new user with the provided data
        User newUser = new User();
        newUser.setUsername(registerRequest.getEmail());
        newUser.setPassword(registerRequest.getPassword()); // Don't forget to hash the password
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPrenom(registerRequest.getPrenom());
        newUser.setNom(registerRequest.getNom());

        userService.saveUser(newUser); // Save the user to the database
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "User registered successfully"));
    }

}
