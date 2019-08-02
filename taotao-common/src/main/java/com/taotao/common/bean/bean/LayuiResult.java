package com.taotao.common.bean.bean;

import lombok.Data;

import java.util.List;

@Data
public class LayuiResult<T> {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;

    public LayuiResult(Integer code, String msg, Integer count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
}
