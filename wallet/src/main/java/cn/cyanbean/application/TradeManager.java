package cn.cyanbean.application;

import cn.cyanbean.domain.TradeService;
import cn.cyanbean.domain.model.TradeRecord;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TradeManager {
    private final TradeService tradeService;

    public TradeManager(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @Transactional(rollbackFor = Exception.class)
    public TradeRecord recharge(TradeRecord tradeRecord){
        Preconditions.checkNotNull(tradeRecord.getWallet(), "参数错误");
        return tradeService.recharge(tradeRecord);
    }

    @Transactional(rollbackFor = Exception.class)
    public TradeRecord consume(TradeRecord tradeRecord){
        Preconditions.checkNotNull(tradeRecord.getWallet(), "参数错误");
        return tradeService.consume(tradeRecord);
    }
}
