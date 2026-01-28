package com.zrp.toyproject0.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zrp.toyproject0.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
