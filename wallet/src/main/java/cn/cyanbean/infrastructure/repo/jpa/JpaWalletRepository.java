package cn.cyanbean.infrastructure.repo.jpa;

import cn.cyanbean.domain.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWalletRepository extends JpaRepository<Wallet, String> {
}
