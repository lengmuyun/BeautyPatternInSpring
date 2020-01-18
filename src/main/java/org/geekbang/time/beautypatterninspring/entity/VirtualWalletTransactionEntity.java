package org.geekbang.time.beautypatterninspring.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.geekbang.time.beautypatterninspring.ienum.Status;
import org.geekbang.time.beautypatterninspring.ienum.Type;

import java.math.BigDecimal;

/**
 * 钱包交易流水
 */
@Data
@TableName("virtual_wallet_transaction")
public class VirtualWalletTransactionEntity {

    /** 钱包交易流水id */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 交易时间 */
    private Long createTime;

    /** 交易金额 */
    private BigDecimal amount;

    /** 交易类型 */
    private Type type;

    /** 出账钱包账号 */
    private Long fromWalletId;

    /** 入账钱包账号 */
    private Long toWalletId;

    /** 交易状态 */
    private Status status;

}
