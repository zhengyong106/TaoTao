package com.taotao.manage.pojo;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BasePojo {
    private Date created;
    private Date updated;
}
