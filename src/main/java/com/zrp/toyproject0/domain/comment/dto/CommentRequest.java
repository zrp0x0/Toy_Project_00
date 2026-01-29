package com.zrp.toyproject0.domain.comment.dto;

import com.zrp.toyproject0.domain.comment.entity.Comment;
import com.zrp.toyproject0.domain.item.entity.Item;
import com.zrp.toyproject0.domain.member.entity.Member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {
    
    @NotBlank(message = "댓글 내용은 비어있을 수 없습니다.") // 공백 및 null 방지
    @Size(max = 500, message = "댓글은 최대 500자까지 입력 가능합니다.") // 길이 제한
    private String content;
    private Long itemId;

    // DTO => Entity
    public Comment toEntity(Item itemEntity, Member memberEntity) {
        Comment comment = Comment.builder()
            .content(this.content)
            .item(itemEntity)
            .member(memberEntity)
            .build();

        itemEntity.getComments().add(comment);
        memberEntity.getComments().add(comment);

        return comment;
    }

}
