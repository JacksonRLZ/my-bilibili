package com.bilibili.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户基本信息表
 *
 * @TableName t_user_info
 */
@Data
@ToString
public class UserInfo implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 昵称
     */
    private String nick;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 签名
     */
    private String sign;
    /**
     * 性别：0男 1女 2未知
     */
    private String gender;
    /**
     * 生日
     */
    private String birth;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否互相关注
     */
    private Boolean followed;

}
