package com.zrp.toyproject0.domain.item.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zrp.toyproject0.domain.item.dto.ItemRequest;
import com.zrp.toyproject0.domain.item.dto.ItemResponse;
import com.zrp.toyproject0.domain.item.service.ItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    // 상품 생성
    @GetMapping("/item/create") 
    public String itemCreate(
        Model model
    ) {
        model.addAttribute("itemForm", new ItemRequest());
        return "itemCreate.html";
    }

    @PostMapping("/item/create")
    public String itemCreate(
        @Valid ItemRequest itemRequest,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "itemCreate.html";
        }

        Long id = itemService.createItem(itemRequest);
        redirectAttributes.addAttribute("id", id);

        return "redirect:/item/detail/{id}";
    }
    

    // 상품 조회
    @GetMapping("/item/list")
    public String itemList(
        Model model
    ) {
        List<ItemResponse> items = itemService.findAll();
        model.addAttribute("items", items);
        return "itemList.html";
    }

    @GetMapping("/item/detail/{id}")
    public String itemDetail(
        @PathVariable("id") Long id,
        Model model
    ) {
        ItemResponse itemResponse = itemService.detailItem(id);
        if (itemResponse != null) {
            model.addAttribute("item", itemResponse);
            return "itemDetail.html";
        } else {
            return "redirect:/item/list";
        }
    }


    // 상품 수정
    @GetMapping("/item/update/{id}")
    public String itemUpdate(
        @PathVariable("id") Long id,
        Model model        
    ) {
        ItemResponse itemResponse = itemService.detailItem(id);
        ItemRequest itemRequest = ItemRequest.from(itemResponse);

        model.addAttribute("itemForm", itemRequest);
        model.addAttribute("itemId", id);

        return "itemUpdate.html";
    }

    @PatchMapping("/item/update/{id}")
    public String itemUpdate(
        @PathVariable("id") Long id,
        @Valid ItemRequest itemRequest,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "itemUpdate.html";
        }

        itemService.updateItem(itemRequest, id);
        redirectAttributes.addAttribute("id", id);

        return "redirect:/item/detail/{id}";
    }
    

    // 상품 삭제
    @DeleteMapping("/item/delete/{id}")
    public String itemDelete(
        @PathVariable("id") Long id
    ) {
       itemService.deleteItem(id);     
        return "redirect:/item/list";
    }

}

