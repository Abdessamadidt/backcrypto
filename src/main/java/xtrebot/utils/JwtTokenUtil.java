package xtrebot.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY = "your_secret_key";

    public static String generateToken(String username, String email, String prenom, String nom,double usdtSolde, double btcSolde) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("prenom", prenom);
        claims.put("nom", nom);
        claims.put("usdtSolde", usdtSolde);
        claims.put("btcSolde", btcSolde);


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expiration après 10 heures
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
