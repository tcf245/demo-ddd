package cn.cyanbean.domain.repository;

import cn.cyanbean.domain.model.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletRepository {
    Wallet save(Wallet wallet);

    Wallet findById(String walletId);

    Optional<List<Wallet>> findAll();
}
