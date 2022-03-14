package lab3.customer.dto;

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
public class GetCustomersResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Customer {

        private int id;
        private String name;

    }


    @Singular
    private List<Customer> customers;

    public static Function<Collection<lab3.customer.entity.Customer>, GetCustomersResponse> entityToDtoMapper() {
        return customers -> {
            GetCustomersResponseBuilder response = GetCustomersResponse.builder();
            customers.stream()
                    .map(customer -> Customer.builder()
                            .id(customer.getId())
                            .name(customer.getName())
                            .build())
                    .forEach(response::customer);
            return response.build();
        };
    }
}
