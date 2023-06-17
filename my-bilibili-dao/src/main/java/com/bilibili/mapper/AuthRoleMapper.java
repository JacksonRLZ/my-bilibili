package com.bilibili.mapper;

import com.bilibili.domain.auth.AuthRole;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 2OpercenT
* @description 针对表【t_auth_role(权限控制--角色表)】的数据库操作Mapper
* @createDate 2023-05-19 23:46:37
* @Entity com.bilibili.domain.auth.AuthRole
*/
@Mapper
public interface AuthRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AuthRole record);

    int insertSelective(AuthRole record);

    AuthRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthRole record);

    int updateByPrimaryKey(AuthRole record);

}
