//package com.jd.helpcode.utils;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.connection.stream.ObjectRecord;
//import org.springframework.data.redis.connection.stream.RecordId;
//import org.springframework.stereotype.Component;
//
///**
// * @author gwl
// * @Type StreamListener.java
// * @Desc
// * @date 2021/8/5 17:15
// */
//@Slf4j
//@Component
//public class StreamListener  implements StreamListener<String, ObjectRecord<String, String>> {
//    @Autowired
//    RedisUtil redisUtil;
//
//    @Override
//    public void onMessage(ObjectRecord<String, String> message) {
//
//        // 消息ID
//        RecordId messageId = message.getId();
//
//
//        log.info("StreamMessageListener  stream message。messageId={}, stream={}", messageId, message.getStream());
//        // 通过RedisTemplate手动确认消息
//        redisUtil.acknowledge("group-1", message);
//
//    }
//
//}
