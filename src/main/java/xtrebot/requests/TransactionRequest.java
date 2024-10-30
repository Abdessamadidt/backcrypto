package xtrebot.requests;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
        private String email;
        private String coin;
        private double amount;
        private String address;
        private String network;
        private String type;

        // Getters et Setter
}
