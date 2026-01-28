package com.zrp.toyproject0.item.service;

import java.util.List;
import com.zrp.toyproject0.item.dto.ItemRequest;
import com.zrp.toyproject0.item.dto.ItemResponse;

public interface ItemService {

    List<ItemResponse> findAll();
    boolean createItem(ItemRequest itemRequest);
    boolean updateItem(ItemRequest itemRequest, Long id);
    ItemResponse detailItem(Long id);
    boolean deleteItem(Long id);
}
