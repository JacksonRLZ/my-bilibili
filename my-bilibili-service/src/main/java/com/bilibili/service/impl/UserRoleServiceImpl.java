package com.bilibili.service.impl;

import com.bilibili.domain.auth.UserRole;
import com.bilibili.mapper.UserRoleMapper;
import com.bilibili.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rlz
 * @date 2023/5/20 10:30
 * @description
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> getUserAuthoritiesByUserId(Long userId) {
        return userRoleMapper.getUserAuthoritiesByUserId(userId);
    }
}
