package xtrebot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xtrebot.entities.Transaction;
import xtrebot.requests.TransactionRequest;
import xtrebot.services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Endpoint pour récupérer toutes les transactions
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
    @GetMapping("/withdrawals")
    public ResponseEntity<List<Transaction>> getAllWithdrawals() {
        List<Transaction> transactions = transactionService.getTransactionsByType("withdraw");
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/deposits")
    public ResponseEntity<List<Transaction>> getAllDeposits() {
        List<Transaction> transactions = transactionService.getTransactionsByType("deposit");
        return ResponseEntity.ok(transactions);
    }

    // Endpoint pour créer une nouvelle transaction (dépôt ou retrait)
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = transactionService.createTransaction(
                transactionRequest.getEmail(),
                transactionRequest.getCoin(),
                transactionRequest.getAmount(),
                transactionRequest.getAddress(),
                transactionRequest.getNetwork(),
                transactionRequest.getType()
        );
        return ResponseEntity.ok(transaction);
    }

    // Endpoint pour mettre à jour le statut d'une transaction
    @PutMapping("/{transactionId}/status")
    public ResponseEntity<Transaction> updateTransactionStatus(
            @PathVariable Long transactionId,
            @RequestParam String status) {
        Transaction transaction = transactionService.updateTransactionStatus(transactionId, status);
        return ResponseEntity.ok(transaction);
    }
}
