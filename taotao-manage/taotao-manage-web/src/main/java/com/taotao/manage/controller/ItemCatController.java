package com.taotao.manage.controller;

import com.taotao.manage.bean.LayuiResult;
import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("item/cat")
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @GetMapping
    public ResponseEntity queryItemCatList(){
        try{
            List<ItemCat> list = itemCatService.queryItemCatListByParentId(0L);
            if(null == list || list.isEmpty()){
                // 资源不存在
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(new LayuiResult<>(0, "Success", list.size(), list));
        } catch (Exception e){
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
