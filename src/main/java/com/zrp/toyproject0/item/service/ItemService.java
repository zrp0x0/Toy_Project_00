package com.zrp.toyproject0.item.service;

import java.util.List;
import com.zrp.toyproject0.item.dto.ItemRequest;
import com.zrp.toyproject0.item.dto.ItemResponse;

public interface ItemService {

    boolean createItem(ItemRequest itemRequest);
    List<ItemResponse> findAll();
    ItemResponse detailItem(Long id);
    boolean updateItem(ItemRequest itemRequest, Long id);
    boolean deleteItem(Long id);
}
