package com.zrp.toyproject0.domain.comment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zrp.toyproject0.domain.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    @Query(value = "select c from Comment c " +
                   "join fetch c.member " + // 댓글을 가져올 때 작성자(member)도 한 번에!
                   "where c.item.id = :itemId",
           countQuery = "select count(c) from Comment c where c.item.id = :itemId")
    Page<Comment> findByItemId(@Param("itemId") Long itemId, Pageable pageable);

    Page<Comment> findByMember_Username(String username, Pageable pageable);

}
