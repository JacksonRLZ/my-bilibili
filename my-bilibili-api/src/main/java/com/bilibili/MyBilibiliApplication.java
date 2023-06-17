package com.bilibili;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author rlz
 * @date 2023/5/8 17:16
 * @description
 */
@SpringBootApplication
@EnableTransactionManagement
public class MyBilibiliApplication {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(MyBilibiliApplication.class, args);
    }
}
