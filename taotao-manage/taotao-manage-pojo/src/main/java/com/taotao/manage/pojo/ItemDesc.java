package com.taotao.manage.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_item_desc")
public class ItemDesc extends BasePojo{
    @Id
    private Long itemId;
    
    private String itemDesc;
}
