package com.bilibili.service.impl;

import com.bilibili.domain.auth.AuthRoleElementOperation;
import com.bilibili.mapper.AuthElementOperationMapper;
import com.bilibili.mapper.AuthRoleElementOperationMapper;
import com.bilibili.service.AuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author rlz
 * @date 2023/5/20 10:31
 * @description
 */
@Service
public class AuthRoleServiceImpl implements AuthRoleService {

    @Autowired
    private AuthRoleElementOperationMapper authRoleElementOperationMapper;

    @Override
    public List<AuthRoleElementOperation> getRoleElementOperationByRoleIds(Set<Long> roleIdSet) {
        return authRoleElementOperationMapper.getRoleElementOperationByRoleIds(roleIdSet);
    }
}
