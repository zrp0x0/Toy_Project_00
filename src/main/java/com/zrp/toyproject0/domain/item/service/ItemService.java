package com.zrp.toyproject0.domain.item.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.zrp.toyproject0.domain.item.dto.ItemRequest;
import com.zrp.toyproject0.domain.item.dto.ItemResponse;
import com.zrp.toyproject0.domain.item.entity.Item;

public interface ItemService {

    Long createItem(ItemRequest itemRequest, Authentication auth);
    List<ItemResponse> findAll();
    ItemResponse detailItem(Long id);
    List<ItemResponse> searchItem(String searchText);
    boolean updateItem(ItemRequest itemRequest, Long id, Authentication auth);
    boolean deleteItem(Long id, Authentication auth);
    
}
