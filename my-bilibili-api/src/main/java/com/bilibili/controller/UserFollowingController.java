package com.bilibili.controller;

/**
 * @author rlz
 * @date 2023/5/15 00:44
 * @description
 */

import com.bilibili.domain.FollowingGroup;
import com.bilibili.domain.JsonResponse;
import com.bilibili.domain.UserFollowing;
import com.bilibili.service.UserFollowingService;
import com.bilibili.support.UserSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserFollowingController {

    @Autowired
    private UserFollowingService userFollowingService;

    @Autowired
    private UserSupport userSupport;

    /**
     * @param userFollowing:
     * @description 关注用户
     * @date 2023/5/15 1:12
     * @return: com.bilibili.domain.JsonResponse<java.lang.String>
     */
    @PostMapping("user-followings")
    public JsonResponse<String> addrUserFollowing(@RequestBody UserFollowing userFollowing) {
        Long userId = userSupport.getCurrentUserId();
        userFollowing.setUserId(userId);
        userFollowingService.addUserFollowings(userFollowing);
        return JsonResponse.success();
    }

    /**
     * @description 查看我的关注
     * @date 2023/5/15 1:13
     * @return: com.bilibili.domain.JsonResponse<java.util.List < com.bilibili.domain.FollowingGroup>>
     */
    @GetMapping("/user-followings")
    public JsonResponse<List<FollowingGroup>> getUserFollowings() {
        Long userId = userSupport.getCurrentUserId();
        List<FollowingGroup> result = userFollowingService.getUserFollowings(userId);
        return new JsonResponse<>(result);
    }

    /**
     * @description 查看我的粉丝
     * @date 2023/5/15 1:13
     * @return: com.bilibili.domain.JsonResponse<java.util.List < com.bilibili.domain.UserFollowing>>
     */
    @GetMapping("/user-fans")
    public JsonResponse<List<UserFollowing>> getUserFans() {
        Long userId = userSupport.getCurrentUserId();
        List<UserFollowing> result = userFollowingService.getUserFans(userId);
        return new JsonResponse<>(result);
    }

    /**
     * @param followingGroup:
     * @description 添加关注组
     * @date 2023/5/15 1:14
     * @return: com.bilibili.domain.JsonResponse<java.lang.Long>
     */
    @PostMapping("/user-following-groups")
    public JsonResponse<Long> addUserFollowingGroups(@RequestBody FollowingGroup followingGroup) {
        Long userId = userSupport.getCurrentUserId();
        followingGroup.setUserId(userId);
        Long groupId = userFollowingService.addUserFollowingGroups(followingGroup);
        return new JsonResponse<>(groupId);
    }

    /**
     * @description 获取用户的关注组
     * @date 2023/5/17 1:34
     * @return: com.bilibili.domain.JsonResponse<java.util.List < com.bilibili.domain.FollowingGroup>>
     */
    @GetMapping("/user-following-groups")
    public JsonResponse<List<FollowingGroup>> getUserFollowingGroups() {
        Long userId = userSupport.getCurrentUserId();
        List<FollowingGroup> list = userFollowingService.getUserFollowingGroups(userId);
        return new JsonResponse<>(list);
    }
}
