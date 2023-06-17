package com.bilibili.mapper;

import com.bilibili.domain.UserMoments;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 2OpercenT
* @description 针对表【t_user_moments(用户动态表)】的数据库操作Mapper
* @createDate 2023-05-18 22:16:35
* @Entity com.bilibili.domain.UserMoments
*/
@Mapper
public interface UserMomentsMapper {

    int deleteByPrimaryKey(Long id);

    Integer addUserMoments(UserMoments record);

    int insertSelective(UserMoments record);

    UserMoments selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMoments record);

    int updateByPrimaryKey(UserMoments record);

}
