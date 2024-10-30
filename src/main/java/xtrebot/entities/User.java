package xtrebot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import xtrebot.enums.RolesEnum;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String prenom;
    private String nom;
    private RolesEnum role; // Champ de rôle ajouté
    private double usdtSole;
    private double btcSolde;


    // Getters and Setters
    // + constructors, if needed
}
