package cn.cyanbean.domain;

import cn.cyanbean.domain.model.TradeRecord;
import cn.cyanbean.domain.model.Wallet;
import cn.cyanbean.domain.repository.TradeRepository;
import cn.cyanbean.domain.repository.WalletRepository;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

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
        tradeRecord.recharge(wallet);
        walletRepository.save(wallet);
        return tradeRepository.save(tradeRecord);
    }

    @Override
    public TradeRecord consume(TradeRecord tradeRecord) {
        Wallet wallet = walletRepository.findById(tradeRecord.getWallet().getWalletId());
        Preconditions.checkNotNull(wallet, "账户不存在");
        tradeRecord.consume(wallet);
        walletRepository.save(wallet);
        return tradeRepository.save(tradeRecord);
    }
}
