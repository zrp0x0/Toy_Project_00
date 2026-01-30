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

    @Query("select i from Item i " +
       "join fetch i.member " + // 상품 등록자 조인
       "left join fetch i.comments c " + // 댓글 조인 (댓글이 없을 수 있으므로 left join)
       "left join fetch c.member " + // 댓글 작성자 조인
       "where i.id = :id")
    Optional<Item> findByIdWithMemberAndComments(@Param("id") Long id);

    @Query(value = "SELECT * FROM item WHERE MATCH(name) AGAINST(:query IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<Item> searchByNameFullText(@Param("query") String query);
}
