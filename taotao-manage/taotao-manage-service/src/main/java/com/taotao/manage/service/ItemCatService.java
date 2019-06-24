package com.taotao.manage.service;

import com.taotao.manage.mapper.ItemCatMapper;
import com.taotao.manage.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

    public List<ItemCat> queryItemCatListByParentId(Long parentId) {
        ItemCat record = new ItemCat();
        record.setParentId(parentId);
        return itemCatMapper.select(record);
    }
}
