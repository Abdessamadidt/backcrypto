package xtrebot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xtrebot.entities.Transaction;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Méthode pour récupérer toutes les transactions avec un type égal à "w"
    List<Transaction> findByType(String type);

    List<Transaction> findByEmail(String email);
}
