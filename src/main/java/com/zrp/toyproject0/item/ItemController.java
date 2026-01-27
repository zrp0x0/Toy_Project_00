package com.zrp.toyproject0.item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
    
    @GetMapping("/item/list")
    public String itemList() {
        return "itemList.html";
    }

    @GetMapping("/item/create") 
    public String itemCreate() {
        return "itemCreate.html";
    }

    @GetMapping("/item/update")
    public String itemUpdate() {
        return "itemUpdate.html";
    }

}
