package lab3.cateringType.repository;

import lab3.cateringType.entity.CateringType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CateringTypeRepository extends JpaRepository<CateringType, String> {

}
