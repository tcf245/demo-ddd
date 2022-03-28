package cn.cyanbean.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    public enum WalletStatus {
        // 可用
        AVAILABLE,
        // 冻结
        BLOCK,
        // 注销
        DESTROYED,
    }
}
