package lab3.customer.controller;

import lab3.cateringType.entity.CateringType;
import lab3.cateringType.service.CateringTypeService;
import lab3.customer.dto.CreateCustomerRequest;
import lab3.customer.dto.GetCustomerResponse;
import lab3.customer.dto.GetCustomersResponse;
import lab3.customer.dto.UpdateCustomerRequest;
import lab3.customer.entity.Customer;
import lab3.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;


@RestController
@RequestMapping("api/cateringTypes/{cateringTypeName}/customers")
public class CateringTypeCustomerController {


    private CustomerService customerService;

    private CateringTypeService cateringTypeService;


    @Autowired
    public CateringTypeCustomerController(CustomerService customerService, CateringTypeService cateringTypeService) {
        this.customerService = customerService;
        this.cateringTypeService = cateringTypeService;
    }

    @GetMapping
    public ResponseEntity<GetCustomersResponse> getCustomers(@PathVariable("cateringTypeName") String cateringTypeName) {
        Optional<CateringType> cateringType = cateringTypeService.find(cateringTypeName);
        return cateringType.map(value -> ResponseEntity.ok(GetCustomersResponse.entityToDtoMapper().apply(customerService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCustomerResponse> getCustomer(@PathVariable("cateringTypeName") String cateringTypeName,
                                                           @PathVariable("id") int id) {
        return customerService.find(cateringTypeName, id)
                .map(value -> ResponseEntity.ok(GetCustomerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Void> createCustomer(@PathVariable("cateringTypeName") String cateringTypeName,
                                                @RequestBody CreateCustomerRequest request,
                                                UriComponentsBuilder builder) {
        Optional<CateringType> cateringType = cateringTypeService.find(cateringTypeName);
        if (cateringType.isPresent()) {
            Customer customer = CreateCustomerRequest
                    .dtoToEntityMapper(cateringType::get)
                    .apply(request);
            customer = customerService.create(customer);
            return ResponseEntity.created(builder.pathSegment("api", "cateringTypes", "{cateringTypeName}", "customers", "{id}")
                    .buildAndExpand(cateringType.get().getName(), customer.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("cateringTypeName") String cateringTypeName,
                                                @PathVariable("id") int id) {
        Optional<Customer> customer = customerService.find(cateringTypeName, id);
        if (customer.isPresent()) {
            customerService.delete(customer.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("cateringTypeName") String cateringTypeName,
                                                @RequestBody UpdateCustomerRequest request,
                                                @PathVariable("id") int id) {
        Optional<Customer> customer = customerService.find(cateringTypeName, id);
        if (customer.isPresent()) {
            UpdateCustomerRequest.dtoToEntityUpdater().apply(customer.get(), request);
            customerService.update(customer.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
