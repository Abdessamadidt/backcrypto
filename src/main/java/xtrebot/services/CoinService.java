package xtrebot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xtrebot.entities.Coin;
import xtrebot.repositories.CoinRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CoinService {

    @Autowired
    private CoinRepository coinRepository;

    public Coin addCoin(Coin coin) {
        return coinRepository.save(coin);
    }

    public void deleteCoin(Long id) {
        coinRepository.deleteById(id);
    }

    public Coin updateCoin(Long id, Coin updatedCoin) {
        Optional<Coin> existingCoin = coinRepository.findById(id);
        if (existingCoin.isPresent()) {
            Coin coin = existingCoin.get();
            coin.setName(updatedCoin.getName());
            coin.setLitnom(updatedCoin.getLitnom());
            coin.setPrice(updatedCoin.getPrice());
            coin.setPricechange(updatedCoin.getPricechange());
            coin.setImg(updatedCoin.getImg());
            return coinRepository.save(coin);
        } else {
            throw new RuntimeException("Coin not found with id " + id);
        }
    }

    public List<Coin> getAllCoins() {
        return coinRepository.findAll();
    }

    public Optional<Coin> getCoinById(Long id) {
        return coinRepository.findById(id);
    }
}
