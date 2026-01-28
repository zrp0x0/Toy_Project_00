package com.zrp.toyproject0.item.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.zrp.toyproject0.item.dto.ItemRequest;
import com.zrp.toyproject0.item.dto.ItemResponse;
import com.zrp.toyproject0.item.entity.Item;
import com.zrp.toyproject0.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    // 상품 조회
    // TODO: 나중에 판매처 추가하면 N+1 문제 발생할 듯
    @Override
    public List<ItemResponse> findAll() {
        List<Item> result = itemRepository.findAll();
        List<ItemResponse> items = result.stream()
            .map(ItemResponse::from)
            .collect(Collectors.toList());
        return items;
    }

    @Override
    public ItemResponse detailItem(Long id) {
        Optional<Item> result = itemRepository.findById(id);
        if (result.isPresent()) {
            ItemResponse itemResponse = ItemResponse.from(result.get());
            return itemResponse;
        } else {
            return null;
        }
    }


    // 상품 생성
    @Override
    public boolean createItem(ItemRequest itemRequest) {
        Item item = itemRequest.toEntity();
        itemRepository.save(item);
        return true;
    }


    // 상품 수정
    @Override
    public boolean updateItem(ItemRequest itemRequest, Long id) {
        Item item = itemRequest.toEntity();
        item.setId(id);
        itemRepository.save(item);
        return true;
    }


    // 상품 삭제
    @Override
    public boolean deleteItem(Long id) {
        Optional<Item> result = itemRepository.findById(id);
        if (result.isPresent()) {
            itemRepository.delete(result.get());
            return true;
        } else {
            return false;
        }
    }
    
}
