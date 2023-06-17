package com.bilibili.service.impl;

import com.bilibili.domain.auth.AuthRoleElementOperation;
import com.bilibili.domain.auth.UserAuthorities;
import com.bilibili.domain.auth.UserRole;
import com.bilibili.service.AuthRoleService;
import com.bilibili.service.UserAuthService;
import com.bilibili.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author rlz
 * @date 2023/5/20 10:16
 * @description
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AuthRoleService authRoleService;

    @Override
    public UserAuthorities getUserAuthorities(Long userId) {
        List<UserRole> userRoleList = userRoleService.getUserAuthoritiesByUserId(userId);
        //获取角色id
        Set<Long> roleIdSet = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
        //查询操作权限
        List<AuthRoleElementOperation> roleElementOperationList = authRoleService.getRoleElementOperationByRoleIds(roleIdSet);

        return null;
    }
}
