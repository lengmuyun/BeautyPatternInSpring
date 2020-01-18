package org.geekbang.time.beautypatterninspring.repository;

import org.geekbang.time.beautypatterninspring.entity.VirtualWalletEntity;
import org.geekbang.time.beautypatterninspring.mapper.VirtualWalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class VirtualWalletRepository {

    @Autowired
    private VirtualWalletMapper virtualWalletMapper;

    public void save(VirtualWalletEntity virtualWallet) {
        virtualWalletMapper.insert(virtualWallet);
    }

    public VirtualWalletEntity getWalletEntity(Long walletId) {
        return virtualWalletMapper.selectById(walletId);
    }

    public BigDecimal getBalance(Long walletId) {
        VirtualWalletEntity walletEntity = getWalletEntity(walletId);
        return walletEntity == null ? null : walletEntity.getBalance();
    }

    public void updateBalance(Long walletId, BigDecimal balance) {
        VirtualWalletEntity walletEntity = getWalletEntity(walletId);
        if (walletEntity != null) {
            walletEntity.setBalance(balance);
            virtualWalletMapper.updateById(walletEntity);
        }
    }

}
