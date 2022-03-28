package cn.cyanbean.domain.repository;

import cn.cyanbean.domain.event.TradeEvent;
import cn.cyanbean.domain.model.TradeRecord;

import java.util.List;
import java.util.Optional;

public interface TradeRepository {
    TradeRecord save(TradeRecord tradeRecord);

    TradeRecord findByTradeNumber(String tradeNumber);

    Optional<List<TradeRecord>> findAll();

    void sendMQEvent(TradeEvent tradeEvent);
}
