package com.bilibili.mapper;

import com.bilibili.domain.auth.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 2OpercenT
* @description 针对表【t_user_role(用户角色关联表)】的数据库操作Mapper
* @createDate 2023-05-19 23:51:08
* @Entity com.bilibili.domain.auth.UserRole
*/
@Mapper
public interface UserRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<UserRole> getUserAuthoritiesByUserId(Long userId);
}
