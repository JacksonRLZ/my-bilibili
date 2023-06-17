package com.bilibili.domain;

import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @author rlz
 * @date 2023/5/16 23:07
 * @description
 */
@Data
public class PageResult<T> {
    private Integer total;
    private List<T> list;

    public PageResult(Integer total, List<T> list) {
        this.total = total;
        this.list = list;
    }
}
