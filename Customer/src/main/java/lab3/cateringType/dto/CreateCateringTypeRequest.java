package lab3.cateringType.dto;

import lab3.cateringType.entity.CateringType;
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

    public static Function<CreateCateringTypeRequest, CateringType> dtoToEntityMapper() {
        return request -> CateringType.builder()
                .name(request.getName())
                .build();
    }

}
