package com.bilibili.support;

import com.bilibili.exception.ConditionException;
import com.bilibili.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author rlz
 * @date 2023/5/9 15:31
 * @description 从请求头统一获取token令牌
 */
@Component
@Slf4j
public class UserSupport {
    public Long getCurrentUserId() {
        // 获取请求上下文
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = requestAttributes.getRequest().getHeader("token");
        Long userId = TokenUtil.verifyToken(token);
        if (userId < 0) {
            throw new ConditionException("非法用户");
        }
        log.info("成功获取当前用户id：{}", userId);
        return userId;
    }
}
