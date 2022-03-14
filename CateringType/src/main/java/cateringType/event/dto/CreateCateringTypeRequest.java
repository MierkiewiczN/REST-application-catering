package cateringType.event.dto;

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

    public static Function<CateringType, CreateCateringTypeRequest> entityToDtoMapper() {
        return entity -> CreateCateringTypeRequest.builder()
                .name(entity.getName())
                .build();
    }

}
