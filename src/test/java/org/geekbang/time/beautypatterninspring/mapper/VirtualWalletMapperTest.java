package org.geekbang.time.beautypatterninspring.mapper;

import org.geekbang.time.beautypatterninspring.entity.VirtualWalletEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class VirtualWalletMapperTest {

    @Autowired
    private VirtualWalletMapper virtualWalletMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<VirtualWalletEntity> virtualWalletList = virtualWalletMapper.selectList(null);
        virtualWalletList.forEach(System.out::println);
    }

}