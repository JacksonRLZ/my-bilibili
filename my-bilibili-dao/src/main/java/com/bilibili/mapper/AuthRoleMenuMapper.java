package com.bilibili.mapper;

import com.bilibili.domain.auth.AuthRoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 2OpercenT
* @description 针对表【t_auth_role_menu(权限控制--角色页面菜单关联表)】的数据库操作Mapper
* @createDate 2023-05-19 23:48:58
* @Entity com.bilibili.domain.auth.AuthRoleMenu
*/
@Mapper
public interface AuthRoleMenuMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AuthRoleMenu record);

    int insertSelective(AuthRoleMenu record);

    AuthRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthRoleMenu record);

    int updateByPrimaryKey(AuthRoleMenu record);

}
