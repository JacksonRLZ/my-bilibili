package com.bilibili.service;

import com.bilibili.domain.auth.AuthRoleElementOperation;

import java.util.List;
import java.util.Set;

/**
 * @author rlz
 * @date 2023/5/20 10:31
 * @description
 */
public interface AuthRoleService {
    List<AuthRoleElementOperation> getRoleElementOperationByRoleIds(Set<Long> roleIdSet);
}
