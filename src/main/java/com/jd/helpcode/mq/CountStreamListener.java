package com.jd.helpcode.mq;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jd.helpcode.mapper.CodeMapper;
import com.jd.helpcode.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 */
@Slf4j
@Component
public class CountStreamListener implements StreamListener<String, MapRecord<String, String, String>> {
    @Autowired
    RedisUtil redisUtil;

    @Resource
    private CodeMapper codeMapper;

    @Override
    public void onMessage(MapRecord<String, String, String> record) {

        record.getValue().forEach((k, v) -> {
            if (StringUtils.isBlank(k) || StringUtils.isBlank(v)) {
                return;
            }
            codeMapper.addNum(k, v);
        });


    }

}
