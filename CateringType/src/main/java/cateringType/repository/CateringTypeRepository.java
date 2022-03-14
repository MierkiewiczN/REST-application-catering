package cateringType.repository;

import cateringType.entity.CateringType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CateringType entity. Repositories should be used in business layer (e.g.: in services).
 */
@Repository
public interface CateringTypeRepository extends JpaRepository<CateringType, String> {

}
