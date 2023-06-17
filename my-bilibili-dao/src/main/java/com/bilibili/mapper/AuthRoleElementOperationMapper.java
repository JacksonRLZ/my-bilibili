package com.bilibili.mapper;

import com.bilibili.domain.auth.AuthRoleElementOperation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
* @author 2OpercenT
* @description 针对表【t_auth_role_element_operation(权限控制--角色与元素操作关联表)】的数据库操作Mapper
* @createDate 2023-05-19 23:48:12
* @Entity com.bilibili.domain.auth.AuthRoleElementOperation
*/
@Mapper
public interface AuthRoleElementOperationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AuthRoleElementOperation record);

    int insertSelective(AuthRoleElementOperation record);

    AuthRoleElementOperation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthRoleElementOperation record);

    int updateByPrimaryKey(AuthRoleElementOperation record);

    List<AuthRoleElementOperation> getRoleElementOperationByRoleIds(Set<Long> roleIdSet);
}
