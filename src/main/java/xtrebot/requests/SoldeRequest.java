package xtrebot.requests;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter()
public class SoldeRequest {
    private String email;
    private double btcSolde;
    private double usdtSolde;
    // Getters et setters
}