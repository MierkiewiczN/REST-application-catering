package lab3.customer.dto;

import lab3.cateringType.entity.CateringType;
import lab3.customer.entity.Customer;
import lombok.*;
import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCustomerRequest {

    private int id;
    private String name;
    private String address;

    public static Function<CreateCustomerRequest, Customer> dtoToEntityMapper(
            Supplier<CateringType> cateringTypeSupplier) {
        return request -> Customer.builder()
                .name(request.getName())
                .address(request.getAddress())
                .cateringType(cateringTypeSupplier.get())
                .build();
    }

}
