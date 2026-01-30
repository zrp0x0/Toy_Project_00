package com.zrp.toyproject0.domain.item.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // fetch join을 사용하면서 페이징을 할 때는 countQuery를 분리하는 것이 안전합니다.
    @Query(value = "select i from Item i left join fetch i.member", countQuery = "select count(i) from Item i")
    Page<Item> customFindAllPage(Pageable pageable);
    // 카운트 쿼리를 넣는 이유는 fetch join을 사용할 경우 등 성능적 측면 때문에

    // 검색 기능도 페이징이 필요하다면 아래와 같이 구성합니다.
    @Query(value = "SELECT * FROM item WHERE MATCH(name) AGAINST(:query IN NATURAL LANGUAGE MODE)", 
       countQuery = "SELECT count(*) FROM item WHERE MATCH(name) AGAINST(:query IN NATURAL LANGUAGE MODE)",
       nativeQuery = true)
    Page<Item> searchByNameFullTextPage(@Param("query") String query, Pageable pageable);

}
