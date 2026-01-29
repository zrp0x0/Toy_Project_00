package com.zrp.toyproject0.domain.item.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.zrp.toyproject0.domain.item.dto.ItemRequest;
import com.zrp.toyproject0.domain.item.dto.ItemResponse;
import com.zrp.toyproject0.domain.item.entity.Item;
import com.zrp.toyproject0.domain.item.repository.ItemRepository;
import com.zrp.toyproject0.domain.member.dto.MemberResponse;
import com.zrp.toyproject0.domain.member.entity.Member;
import com.zrp.toyproject0.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;


    // 상품 생성
    @Override
    public Long createItem(ItemRequest itemRequest, Authentication auth) {
        Item item = itemRequest.toEntity();
        MemberResponse memberResponse = (MemberResponse)auth.getPrincipal();
        Optional<Member> result = memberRepository.findByUsername(memberResponse.getUsername());
        item.setMember(result.get());

        Long id = itemRepository.save(item).getId();
        return id;
    }


    // 상품 조회
    // TODO: 나중에 판매처 추가하면 N+1 문제 발생할 듯
    @Override
    public List<ItemResponse> findAll() {
        List<Item> result = itemRepository.customFindAll();
        List<ItemResponse> items = result.stream()
            .map(ItemResponse::from)
            .collect(Collectors.toList());
        return items;
    }

    @Override
    public ItemResponse detailItem(Long id) {
        Optional<Item> result = itemRepository.findByIdWithMember(id);
        if (result.isPresent()) {
            ItemResponse itemResponse = ItemResponse.from(result.get());
            return itemResponse;
        } else {
            return null;
        }
    }


    // 상품 수정
    @Override
    public boolean updateItem(ItemRequest itemRequest, Long id, Authentication auth) {
        Item item = itemRequest.toEntity();
        if (!item.getMember().getUsername().equals(auth.getName())) {
            return false;
        }

        item.setId(id);
        itemRepository.save(item);
        return true;
    }


    // 상품 삭제
    @Override
    public boolean deleteItem(Long id, Authentication auth) {
        Optional<Item> result = itemRepository.findById(id);
        if (result.isPresent()) {
            if (!result.get().getMember().getUsername().equals(auth.getName())) {
                return false;
            }
            itemRepository.delete(result.get());
            return true;
        } else {
            return false;
        }
    }
    
}
