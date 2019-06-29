package com.taotao.manage.service;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ItemService extends BaseService<Item> {
    @Autowired
    ItemDescService itemDescService;

    public void saveItem(Item item, String desc) {
        // 保存商品
        Date now = new Date();
        item.setId(null);
        item.setStatus(1);
        item.setCreated(now);
        item.setUpdated(now);
        this.save(item);
        // 保存商品描述
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(now);
        itemDesc.setUpdated(now);
        this.itemDescService.save(itemDesc);
    }
}
