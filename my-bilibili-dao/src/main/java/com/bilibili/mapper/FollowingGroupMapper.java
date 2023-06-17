package com.bilibili.mapper;

import com.bilibili.domain.FollowingGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 2OpercenT
* @description 针对表【t_following_group(用户关注分组表)】的数据库操作Mapper
* @createDate 2023-05-13 18:57:52
* @Entity com.bilibili.domain.TFollowingGroup
*/
@Mapper
public interface FollowingGroupMapper {

    int deleteByPrimaryKey(Long id);

    Integer addFollowingGroup(FollowingGroup record);

    FollowingGroup getById(Long id);

    int updateByPrimaryKeySelective(FollowingGroup record);

    int updateByPrimaryKey(FollowingGroup record);

    FollowingGroup getByType(String type);

    List<FollowingGroup> getByUserId(Long userId);

    List<FollowingGroup> getUserFollowingGroups(Long userId);
}
