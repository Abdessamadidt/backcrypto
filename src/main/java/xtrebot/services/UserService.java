package xtrebot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xtrebot.entities.Coin;
import xtrebot.entities.Transaction;
import xtrebot.entities.User;
import xtrebot.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injection de PasswordEncoder

    public boolean validateUserCredentials(String email, String password) {
        User user = userRepository.findByEmail(email);
        // Vérifie que l'utilisateur existe et que le mot de passe correspond
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        // Encode le mot de passe avant de le sauvegarder
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setBtcSolde(updatedUser.getBtcSolde());
            user.setUsdtSolde(updatedUser.getUsdtSolde());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Coin not found with id " + id);
        }
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null; // Check for null
    }
}

