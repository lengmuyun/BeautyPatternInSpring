package org.geekbang.time.beautypatterninspring.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.geekbang.time.beautypatterninspring.ienum.Status;
import org.geekbang.time.beautypatterninspring.entity.VirtualWalletTransactionEntity;
import org.geekbang.time.beautypatterninspring.mapper.VirtualWalletTransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VirtualWalletTransactionRepository {

    @Autowired
    private VirtualWalletTransactionMapper virtualWalletTransactionMapper;

    public Long saveTransaction(VirtualWalletTransactionEntity transaction) {
        virtualWalletTransactionMapper.insert(transaction);
        return transaction.getId();
    }

    public void updateStatus(Long transactionId, Status status) {
        VirtualWalletTransactionEntity transaction = virtualWalletTransactionMapper.selectById(transactionId);
        if (transaction != null) {
            transaction.setStatus(status);
            virtualWalletTransactionMapper.updateById(transaction);
        }
    }

    public List<VirtualWalletTransactionEntity> getTransactionList(String walletId) {
        QueryWrapper<VirtualWalletTransactionEntity> queryWrapper = new QueryWrapper<VirtualWalletTransactionEntity>()
                .eq("from_wallet_id", walletId).or().eq("to_wallet_id", walletId);
        return virtualWalletTransactionMapper.selectList(queryWrapper);
    }
}
