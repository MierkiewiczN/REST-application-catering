package cateringType.dto;

import cateringType.entity.CateringType;
import lombok.*;

import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCateringTypeResponse {

    private String name;

    private int calories;
    private double price;

    public static Function<CateringType, GetCateringTypeResponse> entityToDtoMapper() {
        return cateringType -> GetCateringTypeResponse.builder()
                .name(cateringType.getName())
                .calories(cateringType.getCalories())
                .price(cateringType.getPrice())
                .build();
    }

}
