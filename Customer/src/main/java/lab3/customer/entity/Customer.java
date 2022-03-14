package lab3.customer.entity;

import lab3.cateringType.entity.CateringType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String name;
    private String address;

    @ManyToOne
    @JoinColumn(name = "cateringType")
    private CateringType cateringType;



}
