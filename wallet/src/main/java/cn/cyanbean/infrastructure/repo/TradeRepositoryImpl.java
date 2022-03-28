package cn.cyanbean.infrastructure.repo;

import cn.cyanbean.domain.event.TradeEvent;
import cn.cyanbean.domain.model.TradeRecord;
import cn.cyanbean.domain.repository.TradeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TradeRepositoryImpl implements TradeRepository {

    @Override
    public TradeRecord save(TradeRecord tradeRecord) {
        return null;
    }

    @Override
    public TradeRecord findByTradeNumber(String tradeNumber) {
        return null;
    }

    @Override
    public Optional<List<TradeRecord>> findAll() {
        return Optional.empty();
    }

    @Override
    public void sendMQEvent(TradeEvent tradeEvent) {

    }
}
