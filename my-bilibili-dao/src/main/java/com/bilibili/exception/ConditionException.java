package com.bilibili.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author rlz
 * @date 2023/5/8 23:40
 * @description
 */
@Getter
@Setter
public class ConditionException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    private String code;

    public ConditionException(String code, String name) {
        super(name);
        this.code = code;
    }

    public ConditionException(String name) {
        super(name);
        code = "500";
    }
}
