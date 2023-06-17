package com.bilibili.service;

import com.bilibili.domain.auth.UserRole;

import java.util.List;

/**
 * @author rlz
 * @date 2023/5/20 10:30
 * @description
 */
public interface UserRoleService {
    List<UserRole> getUserAuthoritiesByUserId(Long userId);
}
