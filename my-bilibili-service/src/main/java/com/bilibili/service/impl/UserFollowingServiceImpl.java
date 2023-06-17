package com.bilibili.service.impl;

import com.bilibili.constant.UserConstant;
import com.bilibili.domain.FollowingGroup;
import com.bilibili.domain.User;
import com.bilibili.domain.UserFollowing;
import com.bilibili.domain.UserInfo;
import com.bilibili.exception.ConditionException;
import com.bilibili.mapper.UserFollowingMapper;
import com.bilibili.service.FollowingGroupService;
import com.bilibili.service.UserFollowingService;
import com.bilibili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author rlz
 * @date 2023/5/13 15:21
 * @description
 */
@Service
public class UserFollowingServiceImpl implements UserFollowingService {

    @Autowired
    public UserFollowingMapper userFollowingMapper;

    @Autowired
    public FollowingGroupService followingGroupService;

    @Autowired
    public UserService userService;

    @Override
    public UserFollowing getById(Long id) {
        return userFollowingMapper.getById(id);
    }

    @Override
    @Transactional
    public void addUserFollowings(UserFollowing userFollowing) {
        Long groupId = userFollowing.getGroupId();
        //1. 关注分组
        if (groupId == null) {
            FollowingGroup followingGroup = followingGroupService.getByType(UserConstant.FOLLOWING_GROUP_TYPE_DEFAULT);
//            FollowingGroup followingGroup1 = Optional.ofNullable(followingGroup).
//                    orElse(new FollowingGroup());
            userFollowing.setGroupId(followingGroup.getId());
        } else {
            FollowingGroup followingGroup = followingGroupService.getById(groupId);
            if (followingGroup == null) {
                throw new ConditionException("关注分组不存在！");
            }
        }
        //2. 关注用户是否存在
        Long followingId = userFollowing.getFollowingId();
        User user = userService.getUserById(followingId);
        if (user == null) {
            throw new ConditionException("关注的用户不存在！");
        }
        userFollowingMapper.deleteUserFollowing(userFollowing.getUserId(), followingId);
        userFollowing.setCreateTime(new Date());
        userFollowingMapper.addUserFollowing(userFollowing);
    }

    @Override
    public List<FollowingGroup> getUserFollowings(Long userId) {
        //1. 获取关注的用户
        List<UserFollowing> userFollowingList = userFollowingMapper.getUserFollowings(userId);
        //1.1 提取关注用户的id
        Set<Long> followingIdSet = userFollowingList.stream()
                .map(UserFollowing::getFollowingId).collect(Collectors.toSet());
        List<UserInfo> userInfoList = new ArrayList<>();
        //2. 查询关注用户的基本信息
        if (followingIdSet.size() > 0) {
            userInfoList = userService.getUserInfoByIds(followingIdSet);
        }
        //3.关注用户id <-> 关注用户的基本信息
        for (UserFollowing userFollowing : userFollowingList) {
            for (UserInfo userInfo : userInfoList) {
                if (userFollowing.getFollowingId().equals(userInfo.getUserId())) {
                    //3.1 设置关注用户信息
                    userFollowing.setUserInfo(userInfo);
                }
            }
        }
        //4. 获取当前用户的关注分组
        List<FollowingGroup> followingGroups = followingGroupService.getByUserId(userId);
        FollowingGroup followingGroup = new FollowingGroup();
        followingGroup.setName(UserConstant.FOLLOWING_GROUP_ALL_NAME);
        //4.1 设置全部关注用户列表
        followingGroup.setFollowingUserInfoList(userInfoList);
        List<FollowingGroup> result = new ArrayList<>();
        result.add(followingGroup);
        //4.2 设置分组关注用户列表
        for (FollowingGroup group : followingGroups) {
            List<UserInfo> infoList = new ArrayList<>();
            for (UserFollowing userFollowing : userFollowingList) {
                if (group.getId().equals(userFollowing.getGroupId())) {
                    infoList.add(userFollowing.getUserInfo());
                }
            }
            group.setFollowingUserInfoList(infoList);
            result.add(group);
        }
        return result;
    }

    @Override
    public List<UserFollowing> getUserFans(Long userId) {
        List<UserFollowing> userFansList = userFollowingMapper.getUserFans(userId);
        //抽取粉丝id
        Set<Long> fansIdSet = userFansList.stream()
                .map(UserFollowing::getUserId).collect(Collectors.toSet());
        List<UserInfo> userInfoList = new ArrayList<>();
        //2. 查询粉丝的基本信息
        if (fansIdSet.size() > 0) {
            userInfoList = userService.getUserInfoByIds(fansIdSet);
        }
        //3.查看粉丝是否是互相关注的状态
        List<UserFollowing> userFollowingsList = userFollowingMapper.getUserFollowings(userId);
        for (UserFollowing userFans : userFansList) {
            //3.1 单向关注状态
            for (UserInfo userInfo : userInfoList) {
                if (userFans.getUserId().equals(userInfo.getUserId())) {
                    userInfo.setFollowed(false);
                    userFans.setUserInfo(userInfo);
                }
            }
            //3.2 互粉状态
            for (UserFollowing userFollowing : userFollowingsList) {
                if (userFollowing.getFollowingId().equals(userFans.getUserId())) {
                    userFans.getUserInfo().setFollowed(true);
                }
            }
        }
        return userFansList;
    }

    @Override
    public Long addUserFollowingGroups(FollowingGroup followingGroup) {
        followingGroup.setCreateTime(new Date());
        followingGroup.setType(UserConstant.FOLLOWING_GROUP_TYPE_USER_DIY);
        followingGroupService.addFollowingGroup(followingGroup);
        return followingGroup.getId();
    }

    @Override
    public List<FollowingGroup> getUserFollowingGroups(Long userId) {
        return followingGroupService.getUserFollowingGroups(userId);
    }

    @Override
    public List<UserInfo> checkFollowingStatus(List<UserInfo> userInfoList, Long userId) {
        List<UserFollowing> userFollowingList = userFollowingMapper.getUserFollowings(userId);
        for (UserInfo userInfo : userInfoList) {
            userInfo.setFollowed(false);
            for (UserFollowing userFollowing : userFollowingList) {
                if (userFollowing.getFollowingId().equals(userInfo.getUserId())){
                    userInfo.setFollowed(true);
                }
            }
        }
        return userInfoList;
    }
}
