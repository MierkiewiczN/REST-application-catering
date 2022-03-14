package lab3.customer.repository;

import lab3.cateringType.entity.CateringType;
import lab3.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByIdAndCateringType(Integer id, CateringType cateringType);

    List<Customer> findAllByCateringType(CateringType cateringType);

}
