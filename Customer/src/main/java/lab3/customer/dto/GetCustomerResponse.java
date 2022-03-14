package lab3.customer.dto;

import lab3.customer.entity.Customer;
import lombok.*;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCustomerResponse {


    private int id;
    private String name;
    private String address;

    public static Function<Customer, GetCustomerResponse> entityToDtoMapper() {
        return customer -> GetCustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(customer.getAddress())
                .build();
    }

}
