package cn.cyanbean.controller.dto;

import cn.cyanbean.domain.model.TradeRecord;
import cn.cyanbean.domain.model.Wallet;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeDTO {
    private String tradeNumber;
    private BigDecimal tradeAmount;
    private TradeRecord.TradeType tradeType;
    private BigDecimal balance;
    private String walletId;
    private TradeRecord.TradeStatus tradeStatus;
    private Date createdAt;

    public static TradeDTO from(TradeRecord tradeRecord){
        if (tradeRecord == null) return null;
        TradeDTO tradeDTO = TradeDTO.builder().build();
        BeanUtils.copyProperties(tradeRecord, tradeDTO);
        tradeDTO.setWalletId(tradeRecord.getWallet().getWalletId());
        return tradeDTO;
    }

    public TradeRecord to(){
        TradeRecord tradeRecord = TradeRecord.builder().build();
        BeanUtils.copyProperties(this, tradeRecord);
        tradeRecord.setWallet(Wallet.builder().walletId(this.walletId).build());
        return tradeRecord;
    }
}
