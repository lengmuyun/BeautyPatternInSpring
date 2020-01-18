package org.geekbang.time.beautypatterninspring.service;

import org.geekbang.time.beautypatterninspring.entity.VirtualWalletEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Rollback
@Transactional
class VirtualWalletServiceTest {

    @Autowired
    private VirtualWalletService virtualWalletService;

    private VirtualWalletEntity virtualWalletOne;

    private VirtualWalletEntity virtualWalletZero;

    @BeforeEach
    void initialize() {
        virtualWalletOne = new VirtualWalletEntity();
        virtualWalletZero = new VirtualWalletEntity();
    }

    @Test
    void save() {
        virtualWalletService.save(virtualWalletZero);
        assertNotNull(virtualWalletZero.getId());
        assertEquals(BigDecimal.ZERO, virtualWalletZero.getBalance());
    }

    @Test
    void transfer() {
        virtualWalletService.save(virtualWalletZero);
        virtualWalletOne.setBalance(BigDecimal.valueOf(100L));
        virtualWalletService.save(virtualWalletOne);

        Long fromWalletId = virtualWalletOne.getId();
        Long toWalletId = virtualWalletZero.getId();
        virtualWalletService.transfer(fromWalletId, toWalletId, BigDecimal.valueOf(100L));
        assertEquals(100L, virtualWalletService.getBalance(toWalletId).longValue());
        assertEquals(0L, virtualWalletService.getBalance(fromWalletId).longValue());
    }

}