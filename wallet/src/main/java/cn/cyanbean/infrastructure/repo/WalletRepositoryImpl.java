package cn.cyanbean.infrastructure.repo;

import cn.cyanbean.domain.event.TradeEvent;
import cn.cyanbean.domain.model.TradeRecord;
import cn.cyanbean.domain.model.Wallet;
import cn.cyanbean.domain.repository.TradeRepository;
import cn.cyanbean.domain.repository.WalletRepository;
import cn.cyanbean.infrastructure.repo.jpa.JpaWalletRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WalletRepositoryImpl implements WalletRepository {

    private JpaWalletRepository jpaWalletRepository;

    public WalletRepositoryImpl(JpaWalletRepository jpaWalletRepository) {
        this.jpaWalletRepository = jpaWalletRepository;
    }

    @Override
    public Wallet save(Wallet wallet) {
        return jpaWalletRepository.save(wallet);
    }

    @Override
    public Wallet findById(String walletId) {
        return jpaWalletRepository.findById(walletId).orElse(null);
    }

    @Override
    public Optional<List<Wallet>> findAll() {
        return Optional.of(jpaWalletRepository.findAll());
    }
}
