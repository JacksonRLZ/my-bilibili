package com.bilibili.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.TimeUnit;

/**
 * @author rlz
 * @date 2023/5/18 19:45
 * @description
 */
@Slf4j
public class RocketMQUtil {
    /**
     * @param producer:
     * @param msg
     * @description 同步发送消息
     * @date 2023/5/18 21:57
     * @return: void
     */
    public static void syncSendMsg(DefaultMQProducer producer, Message msg) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        SendResult send = producer.send(msg);
        log.info("同步发送结果：{}", send);
    }

    /**
     * @param producer:
     * @param msg
     * @description 异步发送消息
     * @date 2023/5/18 21:57
     * @return: void
     */
    public static void asyncSendMsg(DefaultMQProducer producer, Message msg) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        int msgCount = 2;
        CountDownLatch2 countDownLatch2 = new CountDownLatch2(msgCount);
        for (int i = 0; i < msgCount; i++) {
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch2.countDown();
                    log.info("异步回调成功：{}", sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    countDownLatch2.countDown();
                    log.error("发送消息发生异常：{}", e);
                    e.printStackTrace();
                }
            });
        }
        countDownLatch2.await(5, TimeUnit.SECONDS);
    }
}
