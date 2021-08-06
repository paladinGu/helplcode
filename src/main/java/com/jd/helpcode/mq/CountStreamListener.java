package com.jd.helpcode.mq;

import com.jd.helpcode.common.Constant;
import com.jd.helpcode.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author gwl
 * @Type StreamListener.java
 * @Desc
 * @date 2021/8/5 17:15
 */
@Slf4j
@Component
public class CountStreamListener implements StreamListener<String, MapRecord<String, String, String>> {
    @Autowired
    RedisUtil redisUtil;


    @Override
    public void onMessage(MapRecord<String,String, String> entries) {

        // 消息ID
        RecordId messageId = entries.getId();

        log.info("StreamMessageListener  stream message。messageId={}", messageId);
        // 通过RedisTemplate手动确认消息
        redisUtil.acknowledge(Constant.ADD_NUM_QUEUE, entries);

    }

}
