package com.bilibili.domain.auth;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户角色关联表
 * @TableName t_user_role
 */
@Data
public class UserRole implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 角色名称
     */
    private AuthRole authRole;


    private static final long serialVersionUID = 1L;
}