package com.bilibili.service;

import com.bilibili.domain.FollowingGroup;
import com.bilibili.domain.UserFollowing;
import com.bilibili.domain.UserInfo;

import java.util.List;

/**
 * @author rlz
 * @date 2023/5/13 15:21
 * @description
 */
public interface UserFollowingService {
    UserFollowing getById(Long id);

    void addUserFollowings(UserFollowing userFollowing);

    /**
     * @param userId:
     * @description 获取用户关注列表
     * @date 2023/5/13 22:46
     * @return: java.util.List<com.bilibili.domain.FollowingGroup>
     */
    List<FollowingGroup> getUserFollowings(Long userId);

    /**
     * @param userId:
     * @description 获取用户的粉丝
     * @date 2023/5/14 21:44
     * @return: java.util.List<com.bilibili.domain.UserFollowing>
     */
    List<UserFollowing> getUserFans(Long userId);

    /**
     * @param followingGroup:
     * @description 添加用户分组
     * @date 2023/5/15 1:15
     * @return: java.lang.Long
     */
    Long addUserFollowingGroups(FollowingGroup followingGroup);

    /**
     * @param userId:
     * @description 获取关注分组
     * @date 2023/5/16 22:46
     * @return: java.util.List<com.bilibili.domain.FollowingGroup>
     */
    List<FollowingGroup> getUserFollowingGroups(Long userId);

    /**
     * @param list:
     * @param userId
     * @description 检查用户与当前用户关注状态
     * @date 2023/5/16 23:37
     * @return: java.util.List<com.bilibili.domain.UserInfo>
     */
    List<UserInfo> checkFollowingStatus(List<UserInfo> list, Long userId);
}
