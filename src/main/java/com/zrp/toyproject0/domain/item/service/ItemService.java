package com.zrp.toyproject0.domain.item.service;

import java.util.List;

import com.zrp.toyproject0.domain.item.dto.ItemRequest;
import com.zrp.toyproject0.domain.item.dto.ItemResponse;

public interface ItemService {

    Long createItem(ItemRequest itemRequest);
    List<ItemResponse> findAll();
    ItemResponse detailItem(Long id);
    boolean updateItem(ItemRequest itemRequest, Long id);
    boolean deleteItem(Long id);
    
}
