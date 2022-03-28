package cn.cyanbean.infrastructure.repo.jpa;

import cn.cyanbean.domain.model.TradeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTradeRepository extends JpaRepository<TradeRecord, Integer> {
}
