package com.bilibili.mapper;

import com.bilibili.domain.User;
import com.bilibili.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author rlz
 * @date 2023/5/8 23:54
 * @description
 */
@Mapper
public interface UserMapper {
    User getUserByPhoneOrEmail(@Param("phone") String phone, @Param("email") String email);

    Integer addUser(User user);

    Integer addUserInfo(UserInfo userInfo);

    User getUserById(Long userId);

    UserInfo getUserInfoById(Long userId);

    Integer updateUsers(User user);

    Integer updateUserInfos(UserInfo userInfo);

    List<UserInfo> getUserInfoByIds(Set<Long> followingIdSet);

    Integer pageCountUserInfo(Map<String,Object> params);

    List<UserInfo> pageListUserInfos(Map<String,Object> params);
}
