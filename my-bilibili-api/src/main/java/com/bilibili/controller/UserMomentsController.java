package com.bilibili.controller;

import com.bilibili.domain.JsonResponse;
import com.bilibili.domain.UserMoments;
import com.bilibili.service.UserMomentsService;
import com.bilibili.support.UserSupport;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author rlz
 * @date 2023/5/18 22:07
 * @description
 */
@RestController
public class UserMomentsController {

    @Autowired
    private UserMomentsService userMomentsService;

    @Autowired
    private UserSupport userSupport;

    @PostMapping("user-moments")
    public JsonResponse<String> addUserMoments(@RequestBody UserMoments userMoments) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        Long userId = userSupport.getCurrentUserId();
        userMoments.setUserId(userId);
        userMomentsService.addUserMoments(userMoments);
        return JsonResponse.success();
    }

    @GetMapping("user-subscribed-moments")
    public JsonResponse<List<UserMoments>> getUserSubscribeMoments()  {
        Long userId = userSupport.getCurrentUserId();
        List<UserMoments> userMomentsList = userMomentsService.getUserSubscribeMoments(userId);
        return new JsonResponse<>(userMomentsList);
    }
}
