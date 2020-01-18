package org.geekbang.time.beautypatterninspring.service.impl;

import org.geekbang.time.beautypatterninspring.domain.VirtualWallet;
import org.geekbang.time.beautypatterninspring.ienum.Status;
import org.geekbang.time.beautypatterninspring.ienum.Type;
import org.geekbang.time.beautypatterninspring.entity.VirtualWalletEntity;
import org.geekbang.time.beautypatterninspring.entity.VirtualWalletTransactionEntity;
import org.geekbang.time.beautypatterninspring.exception.InsufficientBalanceException;
import org.geekbang.time.beautypatterninspring.repository.VirtualWalletRepository;
import org.geekbang.time.beautypatterninspring.repository.VirtualWalletTransactionRepository;
import org.geekbang.time.beautypatterninspring.service.VirtualWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VirtualWalletServiceImpl implements VirtualWalletService {

    @Autowired
    private VirtualWalletRepository walletRepo;

    @Autowired
    private VirtualWalletTransactionRepository transactionRepo;

    @Override
    public void save(VirtualWalletEntity virtualWallet) {
        walletRepo.save(virtualWallet);
    }

    @Override
    public VirtualWallet getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        return convert(walletEntity);
    }

    @Override
    public BigDecimal getBalance(Long walletId) {
        return walletRepo.getBalance(walletId);
    }

    @Override
    public void debit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.debit(amount);
        walletRepo.updateBalance(walletId, wallet.getBalance());
    }

    private VirtualWallet convert(VirtualWalletEntity walletEntity) {
        VirtualWallet wallet = new VirtualWallet(walletEntity.getId());
        wallet.setCreateTime(walletEntity.getCreateTime());
        wallet.setBalance(walletEntity.getBalance());
        return wallet;
    }

    @Override
    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.credit(amount);
        walletRepo.updateBalance(walletId, wallet.getBalance());
    }

    @Override
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setType(Type.PAY);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setFromWalletId(fromWalletId);
        transactionEntity.setToWalletId(toWalletId);
        transactionEntity.setStatus(Status.TO_BE_EXECUTED);
        Long transactionId = transactionRepo.saveTransaction(transactionEntity);
        try {
            debit(fromWalletId, amount);
            credit(toWalletId, amount);
        } catch (InsufficientBalanceException e) {
            transactionRepo.updateStatus(transactionId, Status.CLOSED);
            throw e;
        } catch (Exception e) {
            transactionRepo.updateStatus(transactionId, Status.FAILED);
            throw e;
        }
        transactionRepo.updateStatus(transactionId, Status.EXECUTED);
    }

    @Override
    public List<VirtualWalletTransactionEntity> getTransactionList(String walletId) {
        return transactionRepo.getTransactionList(walletId);
    }

}
