package com.taotao.manage.controller;

import com.taotao.manage.pojo.ItemCategory;
import com.taotao.manage.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("item/cat")
@Controller
public class ItemCategoryController {
    @Autowired
    private ItemCategoryService itemCategoryService;

    @GetMapping
    public ResponseEntity queryItemCategoryList(@RequestParam(value = "parentId", defaultValue = "0") Long parentId){
        try{
            ItemCategory record = new ItemCategory();
            record.setParentId(parentId);
            List<ItemCategory> list = itemCategoryService.queryList(record);
            if(null == list || list.isEmpty()){
                // 资源不存在
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(list);
        } catch (Exception e){
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
