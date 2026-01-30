package com.zrp.toyproject0.domain.comment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import com.zrp.toyproject0.domain.comment.dto.CommentRequest;
import com.zrp.toyproject0.domain.comment.dto.CommentResponse;

public interface CommentService {
    
    void createComment(CommentRequest commentRequest, Authentication auth);
    Page<CommentResponse> findByItemId(Long itemId, Pageable pageable);

}
