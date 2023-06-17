package com.bilibili.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bilibili.constant.RedisConstants;
import com.bilibili.constant.UserMomentsConstants;
import com.bilibili.domain.UserMoments;
import com.bilibili.mapper.UserMomentsMapper;
import com.bilibili.service.UserMomentsService;
import com.bilibili.util.RocketMQUtil;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * @author rlz
 * @date 2023/5/18 22:19
 * @description
 */
@Service
public class UserMomentsServiceImpl implements UserMomentsService {

    @Autowired
    private UserMomentsMapper userMomentsMapper;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void addUserMoments(UserMoments userMoments) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        userMoments.setCreateTime(new Date());
        userMomentsMapper.addUserMoments(userMoments);
        DefaultMQProducer producer = (DefaultMQProducer) applicationContext.getBean("momentsProducer");
        Message message = new Message(UserMomentsConstants.TOPIC_MOMENTS,
                JSONObject.toJSONString(userMoments).getBytes(StandardCharsets.UTF_8));
        RocketMQUtil.syncSendMsg(producer, message);
    }

    @Override
    public List<UserMoments> getUserSubscribeMoments(Long userId) {
        String key = RedisConstants.SUBSCRIBE + userId;
        String s = redisTemplate.opsForValue().get(key);
        return JSONArray.parseArray(s, UserMoments.class);
    }
}
