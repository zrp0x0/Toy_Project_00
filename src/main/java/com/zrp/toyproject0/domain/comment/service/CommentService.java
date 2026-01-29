package com.zrp.toyproject0.domain.comment.service;

import org.springframework.security.core.Authentication;

import com.zrp.toyproject0.domain.comment.dto.CommentRequest;

public interface CommentService {
    
    void createComment(CommentRequest commentRequest, Authentication auth);

}
