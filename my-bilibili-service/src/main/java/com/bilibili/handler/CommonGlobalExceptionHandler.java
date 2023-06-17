package com.bilibili.handler;

import com.bilibili.domain.JsonResponse;
import com.bilibili.exception.ConditionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rlz
 * @date 2023/5/8 23:37
 * @description
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE) //最高优先级
@Slf4j
public class CommonGlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResponse<String> CommonExceptionHandler(HttpServletRequest request, Exception e) {
        String errorMsg = e.getMessage();
        if (e instanceof ConditionException) {
            String errorCode = ((ConditionException) e).getCode();
            log.warn("异常：{}", errorMsg);
            return new JsonResponse<>(errorCode, errorMsg);
        } else {
            log.warn("服务器异常：{}",errorMsg);
            return new JsonResponse<>("500", errorMsg);
        }
    }
}
