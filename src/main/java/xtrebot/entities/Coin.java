package xtrebot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String litnom;
    private double price;
    private double pricechange;
    private String img; // pour stocker le chemin de l'image ou l'URL de l'image
    private String address;
    private String network;

    // Getters et Setters
}
