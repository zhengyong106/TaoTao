package com.taotao.manage.controller;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ItemCategoryController.class);

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Void> saveItem(Item item, @RequestParam(value = "desc") String desc){
        logger.info("新增商品，item = {}，desc = {}", item, desc);
        if(item.getCid() == null || StringUtil.isEmpty(desc)){
            // 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            this.itemService.saveItem(item, desc);
            // 201
            logger.info("新增商品成功，itemId = {}", item.getId());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            logger.info("新增商品失败，title = " + item.getTitle() + ", cid = " + item.getCid(), e);
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
