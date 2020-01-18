package org.geekbang.time.beautypatterninspring.ienum;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum Type {

    RECHARGE(1, "充值"), WITHDRAW(2, "提现"), PAY(3, "支付");

    @EnumValue
    private final int type;

    private final String desc;

    Type(int value, String desc) {
        this.type = value;
        this.desc = desc;
    }

}
