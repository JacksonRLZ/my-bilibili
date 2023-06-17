package com.bilibili.service;

import com.bilibili.domain.auth.UserAuthorities;

/**
 * @author rlz
 * @date 2023/5/20 10:16
 * @description
 */
public interface UserAuthService {
    /**
     * @param userId:
     * @description 获取用户权限
     * @date 2023/5/20 10:28
     * @return: com.bilibili.domain.auth.UserAuthorities
     */
    UserAuthorities getUserAuthorities(Long userId);
}
