package lab3.customer.service;

import lab3.cateringType.entity.CateringType;
import lab3.cateringType.repository.CateringTypeRepository;
import lab3.customer.entity.Customer;
import lab3.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    private CateringTypeRepository cateringTypeRepository;


    @Autowired
    public CustomerService(CustomerRepository customerRepository, CateringTypeRepository cateringTypeRepository) {
        this.customerRepository = customerRepository;
        this.cateringTypeRepository = cateringTypeRepository;
    }

    public Optional<Customer> find(int id) {
        return customerRepository.findById(id);
    }


    public Optional<Customer> find(CateringType cateringType, int id) {
        return customerRepository.findByIdAndCateringType(id, cateringType);
    }


    public Optional<Customer> find(String cateringTypeName, int id) {
        Optional<CateringType> cateringType = cateringTypeRepository.findById(cateringTypeName);
        if (cateringType.isPresent()) {
            return customerRepository.findByIdAndCateringType(id, cateringType.get());
        } else {
            return Optional.empty();
        }
    }


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


    public List<Customer> findAll(CateringType cateringType) {
        return customerRepository.findAllByCateringType(cateringType);
    }


    @Transactional
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }


    @Transactional
    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    @Transactional
    public void delete(int customer) {
        customerRepository.deleteById(customer);
    }


}
