package com.jd.helpcode.mq;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jd.helpcode.model.Code;
import com.jd.helpcode.service.CodeService;
import com.jd.helpcode.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
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
public class UploadStreamListener implements StreamListener<String, MapRecord<String, String, String>> {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private CodeService codeService;

    @Override
    public void onMessage(MapRecord<String, String, String> record) {


        record.getValue().forEach((k, v) -> {
            if (StringUtils.isBlank(k) || StringUtils.isBlank(v)) {
                return;
            }
            LambdaQueryWrapper<Code> wrapper = Wrappers.lambdaQuery();
            Code code = Code.builder().activityCode(k).shareCode(v).build();
            wrapper.eq(Code::getActivityCode, k);
            wrapper.eq(Code::getShareCode, v);
            if (codeService.list(wrapper).size() == 0) {
                codeService.save(code);
            }


        });



    }

}
