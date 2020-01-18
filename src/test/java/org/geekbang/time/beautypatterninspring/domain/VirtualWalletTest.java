package org.geekbang.time.beautypatterninspring.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class VirtualWalletTest {
    
    @Test
    public void testSubtract() {
        BigDecimal bigDecimal = BigDecimal.valueOf(100L);
        BigDecimal subtract = bigDecimal.subtract(BigDecimal.valueOf(10L));
        System.out.println(bigDecimal.longValue() + ", subtract: " + subtract.longValue());
    }

    @Test
    public void testAdd() {
        BigDecimal bigDecimal = BigDecimal.valueOf(100L);
        BigDecimal add = bigDecimal.add(BigDecimal.valueOf(10L));
        System.out.println(bigDecimal.longValue() + ", add: " + add.longValue());
    }

}