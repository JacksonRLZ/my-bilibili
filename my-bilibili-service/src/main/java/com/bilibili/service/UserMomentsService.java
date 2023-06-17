package com.bilibili.service;

import com.bilibili.domain.UserMoments;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * @author rlz
 * @date 2023/5/18 22:19
 * @description
 */
public interface UserMomentsService {

    void addUserMoments(UserMoments userMoments) throws MQBrokerException, RemotingException, InterruptedException, MQClientException;

    /**
     * @description 获取订阅用户的动态信息
     * @date 2023/5/19 20:09
     * @param userId:
     * @return: java.util.List<com.bilibili.domain.UserMoments>
     */
    List<UserMoments> getUserSubscribeMoments(Long userId);
}
