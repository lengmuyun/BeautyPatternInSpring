package org.geekbang.time.beautypatterninspring.domain;

import org.geekbang.time.beautypatterninspring.exception.NoSufficientBalanceException;

import java.math.BigDecimal;

public class VirtualWallet {

    private Long id;
    private Long createTime = System.currentTimeMillis();
    private BigDecimal balance = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void debit(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new NoSufficientBalanceException("");
        }
        this.balance = balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NoSufficientBalanceException("");
        }
        this.balance = balance.add(amount);
    }
}
