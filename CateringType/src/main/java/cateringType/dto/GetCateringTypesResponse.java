package cateringType.dto;

import cateringType.entity.CateringType;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCateringTypesResponse {


    @Singular
    private List<String> cateringTypes;


    public static Function<Collection<CateringType>, GetCateringTypesResponse> entityToDtoMapper() {
        return characters -> {
            GetCateringTypesResponseBuilder response = GetCateringTypesResponse.builder();
            characters.stream()
                    .map(CateringType::getName)
                    .forEach(response::cateringType);
            return response.build();
        };
    }
}
