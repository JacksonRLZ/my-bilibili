package com.bilibili.mapper;

import com.bilibili.domain.auth.AuthElementOperation;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 2OpercenT
* @description 针对表【t_auth_element_operation(权限控制--页面元素操作表)】的数据库操作Mapper
* @createDate 2023-05-19 23:41:23
* @Entity com.bilibili.domain/auth.AuthElementOperation
*/
@Mapper
public interface AuthElementOperationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AuthElementOperation record);

    int insertSelective(AuthElementOperation record);

    AuthElementOperation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AuthElementOperation record);

    int updateByPrimaryKey(AuthElementOperation record);

}
