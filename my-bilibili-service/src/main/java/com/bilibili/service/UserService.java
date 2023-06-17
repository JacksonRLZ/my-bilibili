package com.bilibili.service;

import com.alibaba.fastjson.JSONObject;
import com.bilibili.domain.PageResult;
import com.bilibili.domain.User;
import com.bilibili.domain.UserInfo;

import java.util.List;
import java.util.Set;

/**
 * @author rlz
 * @date 2023/5/8 23:17
 * @description
 */
public interface UserService {

    /**
     * @param user:
     * @description 添加用户
     * @date 2023/5/9 0:03
     * @return: void
     **/
    void addUser(User user);

    /**
     * @param phone:
     * @description 根据手机号获取用户
     * @date 2023/5/9 11:15
     * @return: com.bilibili.domain.User
     **/
    User getUserByPhoneOrEmail(String phone, String email);

    /**
     * @param user:
     * @description 用户登录
     * @date 2023/5/9 11:22
     * @return: java.lang.String
     */
    String login(User user) throws Exception;

    /**
     * @param userId:
     * @description 根据用户id查询用户
     * @date 2023/5/9 15:39
     * @return: com.bilibili.domain.User
     */
    User getUserinfo(Long userId);

    /**
     * @param user:
     * @description 更新用户
     * @date 2023/5/12 18:06
     * @return: void
     */
    void updateUsers(User user) throws Exception;

    /**
     * @param userInfo:
     * @description 更新用户信息
     * @date 2023/5/12 23:08
     * @return: void
     */
    void updateUserInfos(UserInfo userInfo);

    /**
     * @param followingId:
     * @description
     * @date 2023/5/13 22:16
     * @return: com.bilibili.domain.User
     */
    User getUserById(Long followingId);

    /**
     * @param followingIdSet:
     * @description 根据id列表获取用户信息
     * @date 2023/5/13 22:56
     * @return: java.util.List<com.bilibili.domain.UserInfo>
     */
    List<UserInfo> getUserInfoByIds(Set<Long> followingIdSet);

    /**
     * @param params:
     * @description 分页查询用户信息
     * @date 2023/5/16 23:16
     * @return: com.bilibili.domain.PageResult<com.bilibili.domain.UserInfo>
     */
    PageResult<UserInfo> pageListUserInfos(JSONObject params);
}
