package com.bilibili.controller;

import com.bilibili.domain.JsonResponse;
import com.bilibili.domain.User;
import com.bilibili.domain.auth.UserAuthorities;
import com.bilibili.service.UserAuthService;
import com.bilibili.service.UserService;
import com.bilibili.support.UserSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rlz
 * @date 2023/5/20 10:14
 * @description
 */
@RestController
public class UserAuthController {

    @Autowired
    private UserSupport userSupport;

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/user-authorities")
    public JsonResponse<UserAuthorities> getUserAuthorities() {
        Long userId = userSupport.getCurrentUserId();
        UserAuthorities authorities = userAuthService.getUserAuthorities(userId);
        return new JsonResponse<>(authorities);
    }

}
