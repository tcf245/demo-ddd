package cn.cyanbean.infrastructure.repo;

import cn.cyanbean.domain.event.TradeEvent;
import cn.cyanbean.domain.model.TradeRecord;
import cn.cyanbean.domain.repository.TradeRepository;
import cn.cyanbean.infrastructure.repo.jpa.JpaTradeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TradeRepositoryImpl implements TradeRepository {

    private JpaTradeRepository jpaTradeRepository;

    public TradeRepositoryImpl(JpaTradeRepository jpaTradeRepository) {
        this.jpaTradeRepository = jpaTradeRepository;
    }

    @Override
    public TradeRecord save(TradeRecord tradeRecord) {
        return jpaTradeRepository.save(tradeRecord);
    }

    @Override
    public TradeRecord findByTradeNumber(String tradeNumber) {
        return jpaTradeRepository.findByTradeNumber(tradeNumber);
    }

    @Override
    public Optional<List<TradeRecord>> findAll() {
        return Optional.of(jpaTradeRepository.findAll());
    }

    @Override
    public void sendMQEvent(TradeEvent tradeEvent) {

    }
}
