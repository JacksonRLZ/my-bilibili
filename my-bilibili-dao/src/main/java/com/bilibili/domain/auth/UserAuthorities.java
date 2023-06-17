package com.bilibili.domain.auth;

import lombok.Data;

import java.util.List;

/**
 * @author rlz
 * @date 2023/5/20 10:24
 * @description
 */
@Data
public class UserAuthorities {

    private List<AuthRoleElementOperation> roleElementOperationsList;

    private List<AuthRoleMenu> authRoleMenusList;
}
