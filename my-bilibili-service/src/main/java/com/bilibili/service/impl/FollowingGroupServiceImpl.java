package com.bilibili.service.impl;

import com.bilibili.domain.FollowingGroup;
import com.bilibili.mapper.FollowingGroupMapper;
import com.bilibili.service.FollowingGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rlz
 * @date 2023/5/13 15:22
 * @description
 */
@Service
public class FollowingGroupServiceImpl implements FollowingGroupService {
    @Autowired
    public FollowingGroupMapper followingGroupMapper;

    @Override
    public FollowingGroup getByType(String type){
        return followingGroupMapper.getByType(type);
    }
    @Override
    public FollowingGroup getById(Long id){
        return followingGroupMapper.getById(id);
    }

    @Override
    public List<FollowingGroup> getByUserId(Long userId) {
        return followingGroupMapper.getByUserId(userId);
    }

    @Override
    public void addFollowingGroup(FollowingGroup followingGroup) {
        followingGroupMapper.addFollowingGroup(followingGroup);
    }

    @Override
    public List<FollowingGroup> getUserFollowingGroups(Long userId) {
        return followingGroupMapper.getUserFollowingGroups(userId);
    }

}
