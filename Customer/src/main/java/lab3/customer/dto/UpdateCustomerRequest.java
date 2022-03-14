package lab3.customer.dto;

import lab3.customer.entity.Customer;
import lombok.*;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateCustomerRequest {

    private String name;
    private String address;
    public static BiFunction<Customer, UpdateCustomerRequest, Customer> dtoToEntityUpdater() {
        return (customer, request) -> {
            customer.setName(request.getName());
            customer.setAddress(request.getAddress());
            return customer;
        };
    }
}
