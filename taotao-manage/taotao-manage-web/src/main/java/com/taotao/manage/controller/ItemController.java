package com.taotao.manage.controller;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.util.StringUtil;

@RequestMapping("item")
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Void> saveItem(Item item, @RequestParam(value = "desc") String desc){
        if(item.getCid() == null || StringUtil.isEmpty(desc)){
            // 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            this.itemService.saveItem(item, desc);
            // 201
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
