package com.moumouzhandui.mms.utils;

import cn.hutool.core.date.DateUtil;
import com.moumouzhandui.mms.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static com.moumouzhandui.mms.common.RedisPrefixConst.WEBSITE_NAME;

/**
 * 生成订单编号工具类
 *
 * @author 冷血毒舌
 * @create 2022/11/8 15:42
 */

@Slf4j
@Component
public class GenerateOrderUtils {

    @Autowired
    private RedisService redisService;

    private static RedisService staticRedisService;

    @PostConstruct
    public void init() {
        staticRedisService = this.redisService;
    }

    /**
     * 生成订单编号
     * 格式：YYYYMMDD(8位) + 5位自增编号(从00001开始)
     *
     * @return YYYYMMDD + 5位自增编号
     */
    public static String generateOrder() {
        // 生成KEY 格式：yyyy-MM-dd
        String today = DateUtil.today();
        // 通过key查询redis
        Long incr = staticRedisService.hIncr(WEBSITE_NAME, today, 1L);
        if (incr == null) { // 为空则写入新KEY
            staticRedisService.hSet(WEBSITE_NAME, today, 0, getRemainSecondsOneDay(new Date()));
            incr = staticRedisService.hIncr(WEBSITE_NAME, today, 1L);
        }
        DecimalFormat decimalFormat = new DecimalFormat("00000");
        String format = decimalFormat.format(incr);
        return today.replace("-", "") + format;
    }

    private static Integer getRemainSecondsOneDay(Date currentDate) {
        //使用plusDays加传入的时间加1天，将时分秒设置成0
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                        ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        //使用ChronoUnit.SECONDS.between方法，传入两个LocalDateTime对象即可得到相差的秒数
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }

}
