package cn.cyanbean.domain.event;

import cn.cyanbean.domain.model.TradeRecord;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
public class TradeEvent implements Serializable {
    private final TradeRecord tradeRecord;
    private final TradeRecord.TradeStatus tradeStatus;
    private final TradeRecord.TradeType tradeType;

    public TradeEvent(TradeRecord tradeRecord){
        this.tradeRecord = tradeRecord;
        this.tradeStatus = tradeRecord.getTradeStatus();
        this.tradeType = tradeRecord.getTradeType();
    }
}
