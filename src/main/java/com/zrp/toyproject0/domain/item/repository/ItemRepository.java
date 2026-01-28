package com.zrp.toyproject0.domain.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zrp.toyproject0.domain.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
