package cn.cyanbean.domain.model;

import cn.cyanbean.domain.event.TradeEvent;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TradeRecord extends BaseEntity {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tradeId;

    // 交易号
    @Column(unique = true)
    private String tradeNumber;

    // 交易金额
    private BigDecimal tradeAmount;

    // 进出标识
    @Enumerated(EnumType.STRING)
    private TradeFlag tradeFlag;

    // 交易类型
    @Enumerated(EnumType.STRING)
    private TradeType tradeType;

    // 交易金额
    private BigDecimal balance;

    // 账户
    @ManyToOne
    private Wallet wallet;

    // 备注
    private String remark;

    // 交易状态
    private TradeStatus tradeStatus;

    // 原交易号
    private String sourceNumber;

    @DomainEvents
    public List<Object> domainEvents(){
        return Collections.singletonList(new TradeEvent(this));
    }

    public enum TradeFlag {
        IN,
        OUT,
    }

    public enum TradeType {
        RECHARGE,
        CONSUME,
        TRANSFER,
        LOCK,
        RECHARGE_ROLLBACK,
    }

    public enum TradeStatus {
        /**
         * 处理中
         */
        PROCESSING,

        /**
         * 处理完成
         */
        SUCCEED,

        /**
         * 失败
         */
        FAILED,

        /**
         * 作废
         */
        CANCELED
    }

}
