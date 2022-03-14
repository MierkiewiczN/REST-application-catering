package lab3.customer.controller;

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
@RequestMapping("api/customers")
public class CustomerController {

    private CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<GetCustomersResponse> getCustomers() {
        return ResponseEntity.ok(GetCustomersResponse.entityToDtoMapper().apply(customerService.findAll()));
    }


    @GetMapping("{id}")
    public ResponseEntity<GetCustomerResponse> getCustomer(@PathVariable("id") int id) {

        return  customerService.find(id)
                .map(value -> ResponseEntity.ok(GetCustomerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CreateCustomerRequest request, UriComponentsBuilder builder) {

        Customer customer = CreateCustomerRequest
                .dtoToEntityMapper(() -> null)
                .apply(request);
        customer = customerService.create(customer);
        return ResponseEntity.created(builder.pathSegment("api", "customers", "{id}")
                .buildAndExpand(customer.getId()).toUri()).build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") int id) {
        Optional<Customer> customer = customerService.find(id);
        if (customer.isPresent()) {
            customerService.delete(customer.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<Void> updateCustomer(@RequestBody UpdateCustomerRequest request, @PathVariable("id") int id) {
        Optional<Customer> customer = customerService.find(id);
        if (customer.isPresent()) {
            UpdateCustomerRequest.dtoToEntityUpdater().apply(customer.get(), request);
            customerService.update(customer.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
