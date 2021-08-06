package com.jd.helpcode.config;

import com.jd.helpcode.mq.CountStreamListener;
import com.jd.helpcode.mq.UploadStreamListener;
import com.jd.helpcode.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.InetAddress;
import java.time.Duration;
import java.util.Vector;

import static com.jd.helpcode.common.Constant.ADD_NUM_QUEUE;
import static com.jd.helpcode.common.Constant.UPLOAD_CODE_QUEUE;

@Slf4j
@Configuration
public class RedisStreamConfig implements DisposableBean {
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private CountStreamListener countStreamListener;
    @Autowired
    private UploadStreamListener uploadStreamListener;

    @Autowired
    private RedisUtil redisUtil;

    private Vector<StreamMessageListenerContainer<String, MapRecord<String, String, String>>> containerList = new Vector<>();

    private Subscription getSubscription(RedisConnectionFactory factory, StreamListener streamListener, String streamKey, String group) {
        var options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofSeconds(1))
                .executor(threadPoolTaskExecutor)
                .build();
        var listenerContainer = StreamMessageListenerContainer.create(factory, options);
        StreamOffset<String> streamOffset = StreamOffset.create(streamKey, ReadOffset.lastConsumed());

        Consumer consumer = Consumer.from(group, InetAddress.getLoopbackAddress().getHostName());

        Subscription subscription = listenerContainer.receiveAutoAck(consumer, streamOffset, streamListener);
        listenerContainer.start();
        this.containerList.add(listenerContainer);
        return subscription;
    }

    @Bean
    public Subscription subscriptionCountStream(RedisConnectionFactory factory) {

        return getSubscription(factory, countStreamListener, ADD_NUM_QUEUE, ADD_NUM_QUEUE);
    }

    @Bean
    public Subscription subscriptionUploadStream(RedisConnectionFactory factory) {

        return getSubscription(factory, uploadStreamListener, UPLOAD_CODE_QUEUE, UPLOAD_CODE_QUEUE);

    }

    @PostConstruct
    public  void init() throws Exception{
        /**
         * 这里必须先判空，重复创建组会报错，获取不存在的key的组也会报错
         * 所以需要先判断是否存在key，再判断是否存在组
         */
         /**
         * 如果没有消费者组，创建消费者组
         * 如果有消费者组，打印每个消费者的具体信息
         */
        if (redisUtil.hasKey(ADD_NUM_QUEUE)) {
            StreamInfo.XInfoGroups groups = redisUtil.groups(ADD_NUM_QUEUE);
            if (groups.isEmpty()) {
                creatGroup(ADD_NUM_QUEUE,ADD_NUM_QUEUE);
            } else {
                groups.stream().forEach(group -> {
                    log.info("XInfoGroups:{}",group);
                    StreamInfo.XInfoConsumers consumers = redisUtil.consumers(ADD_NUM_QUEUE,group.groupName());
                    log.info("XInfoConsumers:{}",consumers);
                });
            }
        } else {
            creatGroup(ADD_NUM_QUEUE,ADD_NUM_QUEUE);
        }
        if (redisUtil.hasKey(UPLOAD_CODE_QUEUE)) {
            StreamInfo.XInfoGroups groups = redisUtil.groups(UPLOAD_CODE_QUEUE);
            if (groups.isEmpty()) {
                creatGroup(UPLOAD_CODE_QUEUE,UPLOAD_CODE_QUEUE);
            } else {
                groups.stream().forEach(group -> {
                    log.info("XInfoGroups:{}",group);
                    StreamInfo.XInfoConsumers consumers = redisUtil.consumers(UPLOAD_CODE_QUEUE,group.groupName());
                    log.info("XInfoConsumers:{}",consumers);
                });
            }
        } else {
            creatGroup(UPLOAD_CODE_QUEUE,UPLOAD_CODE_QUEUE);
        }
    }

    private void creatGroup(String key, String g) {
        String group = redisUtil.createGroup(key,g);
        log.info("creatGroup:{}",group);
    }
    @Override
    public void destroy()   {
        this.containerList.forEach(StreamMessageListenerContainer::stop);
    }


}
