package com.zrp.toyproject0.domain.item.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.zrp.toyproject0.domain.comment.dto.CommentResponse;
import com.zrp.toyproject0.domain.item.entity.Item;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemResponse {
    
    private Long id;
    private String name;
    private Long price;
    private Integer count;
    private LocalDateTime regDate;
    private String userDisplayName;
    private String username;

    private List<CommentResponse> comments;
    
    // Entity => DTO는 정적 메소드가 좋음
    public static ItemResponse from(Item item) {
        ItemResponse dto = new ItemResponse();
        dto.id = item.getId();
        dto.name = item.getName();
        dto.price = item.getPrice();
        dto.count = item.getCount();
        dto.regDate = item.getRegDate();
        dto.userDisplayName = (item.getMember() != null ? item.getMember().getDisplayName() : "알 수 없는 사용자");
        dto.username = (item.getMember() != null ? item.getMember().getUsername() : "dummy"); // 이렇게 하면 안됨!!
        
        if (item.getComments() != null) {
            dto.comments = item.getComments().stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
        }

        return dto;
    }

}
