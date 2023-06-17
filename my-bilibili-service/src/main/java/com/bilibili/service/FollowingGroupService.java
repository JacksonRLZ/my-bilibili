package com.bilibili.service;

import com.bilibili.domain.FollowingGroup;

import java.util.List;

/**
 * @author rlz
 * @date 2023/5/13 15:21
 * @description
 */
public interface FollowingGroupService {

     FollowingGroup getByType(String type);

     FollowingGroup getById(Long id);

    List<FollowingGroup> getByUserId(Long userId);

    void addFollowingGroup(FollowingGroup followingGroup);

    List<FollowingGroup> getUserFollowingGroups(Long userId);
}
