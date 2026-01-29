package com.zrp.toyproject0.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zrp.toyproject0.domain.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    

}
