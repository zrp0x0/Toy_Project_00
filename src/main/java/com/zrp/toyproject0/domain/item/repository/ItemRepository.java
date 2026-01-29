package com.zrp.toyproject0.domain.item.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zrp.toyproject0.domain.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    
    @Query(value = "select i from Item i left join fetch i.member")
    List<Item> customFindAll();

    @Query("select i from Item i left join fetch i.member where i.id = :id")
    Optional<Item> findByIdWithMember(@Param("id") Long id);

}
