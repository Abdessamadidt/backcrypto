package xtrebot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xtrebot.entities.Coin;

public interface CoinRepository extends JpaRepository<Coin, Long> {
}