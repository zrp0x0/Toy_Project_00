package com.zrp.toyproject0.domain.comment.dto;

import java.time.LocalDateTime;
import com.zrp.toyproject0.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponse {
    
    private Long id;
    private String content;
    private String userDisplayName;
    private LocalDateTime regDate;

    public static CommentResponse from(Comment comment) {
        
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.id = comment.getId();
        commentResponse.content = comment.getContent();
        commentResponse.userDisplayName = (comment.getMember() != null ? comment.getMember().getDisplayName() : "알 수 없는 사용자");
        commentResponse.regDate = comment.getRegDate();

        return commentResponse;
    }

}
