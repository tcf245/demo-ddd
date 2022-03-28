package cn.cyanbean.domain;

import cn.cyanbean.domain.model.TradeRecord;
import cn.cyanbean.domain.model.Wallet;
import cn.cyanbean.domain.repository.TradeRepository;
import cn.cyanbean.domain.repository.WalletRepository;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class TradeServiceImpl implements TradeService {

    private WalletRepository walletRepository;
    private TradeRepository tradeRepository;

    public TradeServiceImpl(WalletRepository walletRepository, TradeRepository tradeRepository) {
        this.walletRepository = walletRepository;
        this.tradeRepository = tradeRepository;
    }

    @Override
    public TradeRecord recharge(TradeRecord tradeRecord) {
        Wallet wallet = walletRepository.findById(tradeRecord.getWallet().getWalletId());
        Preconditions.checkNotNull(wallet, "账户不存在");
        Preconditions.checkState(Wallet.WalletStatus.AVAILABLE.equals(wallet.getWalletStatus()), "账户不可用");

        // 这里应该放到TradeRecord构造方法中
        Preconditions.checkState(tradeRecord.getTradeAmount().compareTo(BigDecimal.ZERO) >= 0, "交易金额不可用");

        tradeRecord.setTradeNumber(UUID.randomUUID().toString());
        tradeRecord.setTradeType(TradeRecord.TradeType.RECHARGE);
        tradeRecord.setTradeFlag(TradeRecord.TradeFlag.IN);
        tradeRecord.setTradeStatus(TradeRecord.TradeStatus.SUCCEED);

        // 这里应该放到Wallet中
        BigDecimal balance = wallet.getBalance().add(tradeRecord.getTradeAmount());
        wallet.setBalance(balance);
        tradeRecord.setWallet(wallet);
        tradeRecord.setBalance(balance);

        return tradeRepository.save(tradeRecord);
    }

    @Override
    public TradeRecord consume(TradeRecord tradeRecord) {
        Wallet wallet = walletRepository.findById(tradeRecord.getWallet().getWalletId());
        Preconditions.checkNotNull(wallet, "账户不存在");
        Preconditions.checkState(Wallet.WalletStatus.AVAILABLE.equals(wallet.getWalletStatus()), "账户不可用");
        Preconditions.checkState(tradeRecord.getTradeAmount().compareTo(BigDecimal.ZERO) >= 0, "交易金额不可用");

        BigDecimal balance = wallet.getBalance().subtract(tradeRecord.getTradeAmount());
        Preconditions.checkState(balance.compareTo(BigDecimal.ZERO) >= 0, "账户余额不足");

        tradeRecord.setTradeNumber(UUID.randomUUID().toString());
        tradeRecord.setTradeFlag(TradeRecord.TradeFlag.OUT);
        tradeRecord.setTradeType(TradeRecord.TradeType.CONSUME);
        tradeRecord.setTradeStatus(TradeRecord.TradeStatus.SUCCEED);

        wallet.setBalance(balance);
        tradeRecord.setWallet(wallet);
        tradeRecord.setBalance(balance);

        return tradeRepository.save(tradeRecord);
    }
}
