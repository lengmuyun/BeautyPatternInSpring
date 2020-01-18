package org.geekbang.time.beautypatterninspring.ienum;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum Status {

    TO_BE_EXECUTED(1, "待执行"), EXECUTED(2, "已执行"), FAILED(3, "失败"), CLOSED(4, "关闭");

    @EnumValue
    private final int status;

    private final String desc;

    Status(int value, String desc) {
        this.status = value;
        this.desc = desc;
    }

}
