package com.bilibili.mapper;

import com.bilibili.domain.auth.AuthMenu;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 2OpercenT
* @description 针对表【t_auth_menu(权限控制-页面访问表)】的数据库操作Mapper
* @createDate 2023-05-19 23:42:43
* @Entity com.bilibili.domain.auth.AuthMenu
*/
@Mapper
public interface AuthMenuMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AuthMenu record);

    int insertSelective(AuthMenu record);

    AuthMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthMenu record);

    int updateByPrimaryKey(AuthMenu record);

}
