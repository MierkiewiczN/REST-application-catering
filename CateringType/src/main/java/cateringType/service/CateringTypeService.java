package cateringType.service;

import cateringType.entity.CateringType;
import cateringType.event.repository.CateringTypeEventRepository;
import cateringType.repository.CateringTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CateringTypeService {

    /**
     * Repository for cateringType management.
     */
    private CateringTypeRepository repository;

    /**
     * Repository for sending events about actions on cateringType entities.
     */
    private CateringTypeEventRepository eventRepository;

    /**
     *
     * @param repository repository for cateringType management
     * @param eventRepository repository for sending events about actions on cateringType entities
     */
    @Autowired
    public CateringTypeService(CateringTypeRepository repository, CateringTypeEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<CateringType> find(String name) {
        return repository.findById(name);
    }

    @Transactional
    public void create(CateringType cateringType) {
        repository.save(cateringType);
        eventRepository.create(cateringType);
    }

    /**
     * @return all available cateringTypes
     */
    public List<CateringType> findAll() {
        return repository.findAll();
    }

    /**
     * Deletes selected cateringType.
     *
     * @param cateringType cateringType to be deleted
     */
    @Transactional
    public void delete(CateringType cateringType) {
        eventRepository.delete(cateringType);
        repository.delete(cateringType);
    }

}
