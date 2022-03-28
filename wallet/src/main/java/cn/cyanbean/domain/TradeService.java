package cn.cyanbean.domain;

import cn.cyanbean.domain.model.TradeRecord;

public interface TradeService {
    /**
     * 充值
     * @param tradeRecord
     * @return
     */
    TradeRecord recharge(TradeRecord tradeRecord);

    /**
     * 消费
     * @param tradeRecord
     * @return
     */
    TradeRecord consume(TradeRecord tradeRecord);
}
