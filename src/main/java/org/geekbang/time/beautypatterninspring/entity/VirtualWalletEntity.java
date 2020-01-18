package org.geekbang.time.beautypatterninspring.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("virtual_wallet")
public class VirtualWalletEntity {

    /** 虚拟钱包id */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 创建时间 */
    private Long createTime;

    /** 余额 */
    private BigDecimal balance;

    public VirtualWalletEntity() {
        this.createTime = System.currentTimeMillis();
        this.balance = BigDecimal.ZERO;
    }

}
