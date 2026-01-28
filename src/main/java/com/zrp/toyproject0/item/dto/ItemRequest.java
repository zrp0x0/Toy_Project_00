package com.zrp.toyproject0.item.dto;

import com.zrp.toyproject0.item.entity.Item;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemRequest {
    
    @NotBlank(message="상품명은 필수 입력 값입니다.")
    private String name;

    @NotNull(message="가격은 필수 입력 값입니다.")
    @Min(value = 100, message = "가격은 최소 100원 이상이어야 합니다.")
    private Long price;

    @NotNull(message = "수량은 필수 입력 값입니다.")
    @Max(value = 999, message = "최대 등록 수량은 999개입니다.")
    private Integer count;

    // DTO => Entity는 일반 메소드가 좋음
    public Item toEntity() {
        Item item = Item.builder()
            .name(this.name)
            .price(this.price)
            .count(this.count)
            .build();

        return item;
    }

}
