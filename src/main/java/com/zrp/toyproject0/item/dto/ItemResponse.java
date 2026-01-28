package com.zrp.toyproject0.item.dto;

import java.time.LocalDateTime;
import com.zrp.toyproject0.item.entity.Item;
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
    
    // 판매처

    public static ItemResponse from(Item item) {
        ItemResponse dto = new ItemResponse();
        dto.id = item.getId();
        dto.name = item.getName();
        dto.price = item.getPrice();
        dto.count = item.getCount();
        dto.regDate = item.getRegDate();
        // 판매처
        return dto;
    }

}
