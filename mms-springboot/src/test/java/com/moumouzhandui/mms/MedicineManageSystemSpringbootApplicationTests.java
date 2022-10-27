package com.moumouzhandui.mms;

import com.moumouzhandui.mms.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedicineManageSystemSpringbootApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    void contextLoads() {
    }

    @Test
    void testRedisServiceSet() {
        redisService.set("name", "张三");
    }

    @Test
    void testRedisServiceGet() {
        System.out.println(redisService.get("name"));
    }


}
