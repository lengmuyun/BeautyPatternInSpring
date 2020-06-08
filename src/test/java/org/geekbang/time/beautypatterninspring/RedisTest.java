package org.geekbang.time.beautypatterninspring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        stringRedisTemplate.opsForValue().set("name", "fkz", 5, TimeUnit.SECONDS);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            // ignore
        }
        assertEquals("fkz", stringRedisTemplate.opsForValue().get("name"));
    }

}
