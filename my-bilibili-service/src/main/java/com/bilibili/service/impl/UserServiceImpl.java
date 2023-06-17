package com.bilibili.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bilibili.constant.UserConstant;
import com.bilibili.domain.PageResult;
import com.bilibili.domain.User;
import com.bilibili.domain.UserInfo;
import com.bilibili.exception.ConditionException;
import com.bilibili.mapper.UserMapper;
import com.bilibili.service.UserService;
import com.bilibili.util.MD5Util;
import com.bilibili.util.RSAUtil;
import com.bilibili.util.TokenUtil;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author rlz
 * @date 2023/5/9 00:03
 * @description
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void addUser(@RequestBody User user) {
        String phone = user.getPhone();
        String email = user.getEmail();
        if (StringUtils.isNullOrEmpty(phone) && StringUtils.isNullOrEmpty(email)) {
            throw new ConditionException("手机号或邮箱不能为空");
        }
        User userDao = getUserByPhoneOrEmail(phone, email);
        if (userDao != null) {
            throw new ConditionException("用户已存在");
        }
        Date date = new Date();
        String salt = String.valueOf(date.getTime());
        String password = user.getPassword();
        String rsaPassword;
        try {
            rsaPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException("密码解密失败");
        }
        String md5Password = MD5Util.sign(rsaPassword, salt, "UTF-8");
        user.setSalt(salt);
        user.setPassword(md5Password);
        user.setCreateTime(date);
        user.setUpdateTime(date);
        userMapper.addUser(user);
        //添加用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setNick(UserConstant.DEFAULT_NICK);
        userInfo.setBirth(UserConstant.DEFAULT_BIRTH);
        userInfo.setGender(UserConstant.GENDER_MALE);
        userInfo.setCreateTime(date);
        userInfo.setUpdateTime(date);
        userMapper.addUserInfo(userInfo);
    }

    public User getUserByPhoneOrEmail(String phone, String email) {
        return userMapper.getUserByPhoneOrEmail(phone, email);
    }

    @Override
    public String login(@RequestBody User user) throws Exception {
        String phone = user.getPhone();
        String email = user.getEmail();
        if (StringUtils.isNullOrEmpty(phone) && StringUtils.isNullOrEmpty(email)) {
            throw new ConditionException("手机号或邮箱不能为空");
        }
        User userDao = getUserByPhoneOrEmail(phone, email);
        if (userDao == null) {
            throw new ConditionException("用户不存在");
        }
        String password = user.getPassword();
        String rsaPassword;
        try {
            rsaPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException("密码解析失败");
        }
        String salt = userDao.getSalt();
        String md5Password = MD5Util.sign(rsaPassword, salt, "UTF-8");
        if (!md5Password.equals(userDao.getPassword())) {
            throw new ConditionException("密码错误");
        }
        return TokenUtil.generateToken(userDao.getId());
    }

    @Override
    public User getUserinfo(@RequestBody Long userId) {
        User user = userMapper.getUserById(userId);
        UserInfo userInfo = userMapper.getUserInfoById(userId);
        user.setUserInfo(userInfo);
        return user;
    }

    @Override
    public void updateUsers(@RequestBody User user) throws Exception {
        Long userId = user.getId();
        User dbUser = userMapper.getUserById(userId);
        if (dbUser == null) {
            throw new ConditionException("用户不存在");
        }
        //有没有修改密码
        if (!StringUtils.isNullOrEmpty(user.getPassword())) {
            String rawPassword = RSAUtil.decrypt(user.getPassword());
            String md5Password = MD5Util.sign(rawPassword, dbUser.getSalt(), "UTF-8");
            user.setPassword(md5Password);
        }
        user.setUpdateTime(new Date());
        userMapper.updateUsers(user);
    }

    @Override
    public void updateUserInfos(UserInfo userInfo) {
        userInfo.setUpdateTime(new Date());
        userMapper.updateUserInfos(userInfo);
    }

    @Override
    public User getUserById(Long followingId) {
        return userMapper.getUserById(followingId);
    }

    @Override
    public List<UserInfo> getUserInfoByIds(Set<Long> followingIdSet) {
        return userMapper.getUserInfoByIds(followingIdSet);
    }

    @Override
    public PageResult<UserInfo> pageListUserInfos(JSONObject params) {
        Integer pageNum = params.getInteger("pageNum");
        Integer pageSize = params.getInteger("pageSize");
        params.put("start", (pageNum - 1) * pageSize);
        params.put("limit", pageSize);
        //先判断符合用户条数有多少
        Integer total = userMapper.pageCountUserInfo(params);
        log.info("查询出用户条数：{}", total);
        List<UserInfo> list = new ArrayList<>();
        if (total > 0) {
            list = userMapper.pageListUserInfos(params);
        }
        return new PageResult<>(total, list);
    }
}
