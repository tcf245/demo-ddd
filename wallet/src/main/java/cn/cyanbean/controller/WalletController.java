package cn.cyanbean.controller;

import cn.cyanbean.domain.model.Wallet;
import cn.cyanbean.domain.repository.WalletRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/wallet")
public class WalletController {
    private WalletRepository walletRepository;

    public WalletController(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @GetMapping("/{walletId}")
    public Wallet findOne(@PathVariable("walletId") String walletId){
        return walletRepository.findById(walletId);
    }

    @GetMapping()
    public List<Wallet> findAll(){
        return walletRepository.findAll().get();
    }

}
