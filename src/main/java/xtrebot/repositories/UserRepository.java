package xtrebot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xtrebot.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email); // Méthode pour trouver un utilisateur par email

}
