package com.bilibili.controller;

import com.alibaba.fastjson.JSONObject;
import com.bilibili.domain.JsonResponse;
import com.bilibili.domain.PageResult;
import com.bilibili.domain.User;
import com.bilibili.domain.UserInfo;
import com.bilibili.service.UserFollowingService;
import com.bilibili.service.UserService;
import com.bilibili.support.UserSupport;
import com.bilibili.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author rlz
 * @date 2023/5/8 23:52
 * @description
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFollowingService userFollowingService;

    @Autowired
    private UserSupport userSupport;

    /**
     * @description 获取用户信息
     * @date 2023/5/9 18:00
     * @return: com.bilibili.domain.JsonResponse<com.bilibili.domain.User>
     */
    @GetMapping("users")
    public JsonResponse<User> getUserInfo() {
        Long userId = userSupport.getCurrentUserId();
        User user = userService.getUserinfo(userId);
        return new JsonResponse<>(user);
    }

    /**
     * @description 获取RSA公钥
     * @date 2023/5/8 23:59
     * @return: com.bilibili.domain.JsonResponse<java.lang.String>
     */
    @GetMapping("rsa-pks")
    public JsonResponse<String> getRsaPublicKey() {
        String pk = RSAUtil.getPublicKeyStr();
        return new JsonResponse<>(pk);
    }

    /**
     * @param user: 用户
     * @description 添加用户
     * @date 2023/5/9 0:00
     * @return: com.bilibili.domain.JsonResponse<java.lang.String>
     */
    @PostMapping("users")
    public JsonResponse<String> registerUser(@RequestBody User user) {
        userService.addUser(user);
        return JsonResponse.success();
    }

    /**
     * @param user:
     * @description 获取用户token
     * @date 2023/5/9 18:00
     * @return: com.bilibili.domain.JsonResponse<java.lang.String>
     */
    @PostMapping("user-tokens")
    public JsonResponse<String> login(@RequestBody User user) throws Exception {
        String token = userService.login(user);
        return new JsonResponse<>(token);
    }

    /**
     * @param user:
     * @description 更新用户
     * @date 2023/5/12 23:01
     * @return: com.bilibili.domain.JsonResponse<java.lang.String>
     */
    @PutMapping("users")
    public JsonResponse<String> updateUsers(@RequestBody User user) throws Exception {
        Long currentUserId = userSupport.getCurrentUserId();
        user.setId(currentUserId);
        userService.updateUsers(user);
        return JsonResponse.success();
    }

    /**
     * @param userInfo:
     * @descrption 更新用户信息
     * @date 2023/5/16 23:06
     * @return: com.bilibili.domain.JsonResponse<java.lang.String>
     */
    @PutMapping("/user-infos")
    public JsonResponse<String> updateUserInfos(@RequestBody UserInfo userInfo) {
        Long userId = userSupport.getCurrentUserId();
        userInfo.setUserId(userId);
        userService.updateUserInfos(userInfo);
        return JsonResponse.success();
    }

    /**
     * @param pageNum:
     * @param pageSize
     * @param nick     昵称
     * @description
     * @date 2023/5/16 23:13
     * @return: com.bilibili.domain.JsonResponse<com.bilibili.domain.PageResult < com.bilibili.domain.UserInfo>>
     */
    @GetMapping("/user-infos")
    public JsonResponse<PageResult<UserInfo>> pageListUserInfos(@RequestParam Integer pageNum,
                                                                @RequestParam Integer pageSize,
                                                                String nick) {
        Long userId = userSupport.getCurrentUserId();
        JSONObject params = new JSONObject();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("nick", nick);
        params.put("userId", userId);
        PageResult<UserInfo> result = userService.pageListUserInfos(params);
        //判断查出的用户有没有被当前用户关注过
        if (result.getTotal() > 0) {
            List<UserInfo> checkFollowingStatus = userFollowingService.checkFollowingStatus(result.getList(), userId);
            result.setList(checkFollowingStatus);
        }
        return new JsonResponse<>(result);
    }
}
