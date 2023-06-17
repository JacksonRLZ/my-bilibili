package com.bilibili.request;

import lombok.Data;

/**
 * @author rlz
 * @date 2023/5/16 23:10
 * @description
 */
@Data
public class PageRequest {
    private Integer pageNum;
    private Integer pageSize;
}
