package com.bilibili.mapper;

import com.bilibili.domain.FollowingGroup;
import com.bilibili.domain.UserFollowing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 2OpercenT
 * @description 针对表【t_user_following(用户关注表)】的数据库操作Mapper
 * @createDate 2023-05-13 00:14:15
 * @Entity com.bilibili.domain.UserFollowing
 */
@Mapper
public interface UserFollowingMapper {

    int deleteByPrimaryKey(Long id);

    int addUserFollowing(UserFollowing record);

    int insertSelective(UserFollowing record);

    UserFollowing getById(Long id);

    int updateByPrimaryKeySelective(UserFollowing record);

    int updateByPrimaryKey(UserFollowing record);

    Integer deleteUserFollowing(@Param("userId") Long userId, @Param("followingId") Long followingId);

    List<UserFollowing> getUserFollowings(Long userId);

    List<UserFollowing> getUserFans(Long userId);
}
