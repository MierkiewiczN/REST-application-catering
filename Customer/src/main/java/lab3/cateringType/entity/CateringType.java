package lab3.cateringType.entity;

import lab3.customer.entity.Customer;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "cateringTypes")
public class CateringType implements Serializable {


    @Id
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "cateringType")
    @ToString.Exclude
    private List<Customer> customers;

}
