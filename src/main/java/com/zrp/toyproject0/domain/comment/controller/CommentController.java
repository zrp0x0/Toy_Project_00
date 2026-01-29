package com.zrp.toyproject0.domain.comment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zrp.toyproject0.domain.comment.dto.CommentRequest;
import com.zrp.toyproject0.domain.comment.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommentController {
    
    private final CommentService commentService;

    @PostMapping("/comment/create")
    @ResponseBody
    public ResponseEntity<String> commentCreate(
        Authentication auth,
        @Valid @RequestBody CommentRequest commentRequest
    ) {
        if (auth == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Required");
        }

        // 클라이언트 사전 로직 TODO
        commentService.createComment(commentRequest, auth);
        return ResponseEntity.ok("Successs");
    }

}
