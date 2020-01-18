package org.geekbang.time.beautypatterninspring.service;

import org.geekbang.time.beautypatterninspring.domain.VirtualWallet;
import org.geekbang.time.beautypatterninspring.entity.VirtualWalletEntity;
import org.geekbang.time.beautypatterninspring.entity.VirtualWalletTransactionEntity;

import java.math.BigDecimal;
import java.util.List;

public interface VirtualWalletService {

    VirtualWallet getVirtualWallet(Long walletId);
    BigDecimal getBalance(Long walletId);
    void debit(Long walletId, BigDecimal amount);
    void credit(Long walletId, BigDecimal amount);
    void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount);

    /**
     * 创建一个虚拟钱包账号
     * @param virtualWallet
     */
    void save(VirtualWalletEntity virtualWallet);

    /**
     * 获取钱包的交易流水
     * @param walletId
     * @return
     */
    List<VirtualWalletTransactionEntity> getTransactionList(String walletId);

}
