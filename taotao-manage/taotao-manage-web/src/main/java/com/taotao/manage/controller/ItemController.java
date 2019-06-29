package com.taotao.manage.controller;

import com.taotao.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("item")
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

}
