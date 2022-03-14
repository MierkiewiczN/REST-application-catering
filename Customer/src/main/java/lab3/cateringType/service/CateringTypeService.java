package lab3.cateringType.service;

import lab3.cateringType.entity.CateringType;
import lab3.cateringType.repository.CateringTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CateringTypeService {

    private CateringTypeRepository repository;

    @Autowired
    public CateringTypeService(CateringTypeRepository repository) {
        this.repository = repository;
    }


    public Optional<CateringType> find(String name) {
        return repository.findById(name);
    }


    @Transactional
    public void create(CateringType cateringType) {
        repository.save(cateringType);
    }

    @Transactional
    public void delete(CateringType cateringType) {
        repository.delete(cateringType);
    }

}
