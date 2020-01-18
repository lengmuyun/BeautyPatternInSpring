package org.geekbang.time.beautypatterninspring.controller;

import org.geekbang.time.beautypatterninspring.entity.VirtualWalletTransactionEntity;
import org.geekbang.time.beautypatterninspring.service.VirtualWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/virtualWallet")
public class VirtualWalletController {

    @Autowired
    private VirtualWalletService virtualWalletService;

    /**
     * 查询余额
     * @param walletId
     * @return
     */
    public BigDecimal getBalance(Long walletId) {
        return virtualWalletService.getBalance(walletId);
    }

    /**
     * 出账
     * @param walletId
     * @param amount
     */
    public void debit(Long walletId, BigDecimal amount) {
        virtualWalletService.debit(walletId, amount);
    }

    /**
     * 入账
     * @param walletId
     * @param amount
     */
    public void credit(Long walletId, BigDecimal amount) {
        virtualWalletService.credit(walletId, amount);
    }

    /**
     * 转账
     * @param fromWalletId
     * @param toWalletId
     * @param amount
     */
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        virtualWalletService.transfer(fromWalletId, toWalletId, amount);
    }

    @RequestMapping("/transaction/list/{walletId}")
    public List<VirtualWalletTransactionEntity> getTransactionList(@PathVariable String walletId) {
        return virtualWalletService.getTransactionList(walletId);
    }

}
