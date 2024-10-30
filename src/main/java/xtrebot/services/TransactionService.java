package xtrebot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xtrebot.entities.Transaction;
import xtrebot.repositories.TransactionRepository;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(String email, String coin, double amount, String address, String network, String type) {
        // Crée la transaction
        Transaction transaction = new Transaction();
        transaction.setEmail(email);
        transaction.setAmount(amount);
        transaction.setDate(new Date());
        transaction.setCoin(coin);
        transaction.setAddress(address);
        transaction.setNetwork(network);
        transaction.setStatus("new");
        transaction.setType(type);

        // Sauvegarde la transaction
        transactionRepository.save(transaction);

        return transaction;
    }

    public Transaction updateTransactionStatus(Long transactionId, String status) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        // Vérifie si le statut est valide
        if (!"in progress".equalsIgnoreCase(status) &&
                !"done".equalsIgnoreCase(status) &&
                !"canceled".equalsIgnoreCase(status)) {
            throw new IllegalArgumentException("Invalid status");
        }

        // Applique le nouveau statut
        transaction.setStatus(status);

        // Règles pour mettre à jour le solde (balance) selon le statut et le type
        if ("done".equalsIgnoreCase(status) && "d".equalsIgnoreCase(transaction.getType())) {
            // Si la transaction est un dépôt et que le statut est "done", ajoute un montant positif dans Balance
        } else if ("canceled".equalsIgnoreCase(status) && "w".equalsIgnoreCase(transaction.getType())) {
            // Si la transaction est un retrait et que le statut est "canceled", ajoute un montant positif pour annuler le retrait
        }

        // Sauvegarde la transaction mise à jour
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByType(String type) {
        return transactionRepository.findByType(type);
    }
}
