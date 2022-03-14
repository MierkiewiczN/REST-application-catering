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
public class CreateCateringTypeRequest {

    private String name;

    private int calories;
    private double price;


    public static Function<CreateCateringTypeRequest, CateringType> dtoToEntityMapper() {
        return request -> CateringType.builder()
                .name(request.getName())
                .calories(request.getCalories())
                .price(request.getPrice())
                .build();
    }

}
