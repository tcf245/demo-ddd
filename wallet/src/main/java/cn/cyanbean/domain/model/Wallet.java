package cn.cyanbean.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Preconditions;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wallet extends BaseEntity {
    // ID
    @Id
    private String walletId;

    // 密码
    private String password;

    // 账户状态
    private WalletStatus walletStatus = WalletStatus.AVAILABLE;

    // 用户id
    private Long userId;

    // 余额
    private BigDecimal balance = BigDecimal.ZERO;

    public Wallet incrementBalance(BigDecimal amount){
        Preconditions.checkState(Wallet.WalletStatus.AVAILABLE.equals(this.getWalletStatus()), "账户不可用");
        Preconditions.checkArgument(amount.compareTo(BigDecimal.ZERO) > 0, "金额必须大于0");
        this.balance.add(amount);
        return this;
    }

    public Wallet decrementBalance(BigDecimal amount){
        Preconditions.checkState(Wallet.WalletStatus.AVAILABLE.equals(this.getWalletStatus()), "账户不可用");
        Preconditions.checkArgument(amount.compareTo(BigDecimal.ZERO) > 0, "金额必须大于0");
        Preconditions.checkArgument(this.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) > 0, "余额不足");
        this.balance.subtract(amount);
        return this;
    }

    public enum WalletStatus {
        // 可用
        AVAILABLE,
        // 冻结
        BLOCK,
        // 注销
        DESTROYED,
    }
}
