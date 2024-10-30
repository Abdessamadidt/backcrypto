package xtrebot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xtrebot.entities.Coin;
import xtrebot.services.CoinService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/coins")
public class CoinController {

    @Autowired
    private CoinService coinService;



    @PostMapping
    public Coin addCoin(@RequestBody Coin coin) {
        return coinService.addCoin(coin);
    }

    @DeleteMapping("/{id}")
    public void deleteCoin(@PathVariable Long id) {
        coinService.deleteCoin(id);
    }

    @PutMapping("/{id}")
    public Coin updateCoin(@PathVariable Long id, @RequestBody Coin updatedCoin) {
        return coinService.updateCoin(id, updatedCoin);
    }

    @GetMapping
    public List<Coin> getAllCoins() {
        return coinService.getAllCoins();
    }

    @GetMapping("/{id}")
    public Coin getCoinById(@PathVariable Long id) {
        return coinService.getCoinById(id).orElseThrow(() -> new RuntimeException("Coin not found"));
    }
}
