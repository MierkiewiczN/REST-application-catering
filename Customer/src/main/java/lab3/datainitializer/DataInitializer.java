package lab3.datainitializer;

import lab3.cateringType.entity.CateringType;
import lab3.cateringType.service.CateringTypeService;
import lab3.customer.entity.Customer;
import lab3.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final CustomerService customerService;
    private final CateringTypeService cateringTypeService;

    @Autowired
    public DataInitializer(CustomerService customerService, CateringTypeService cateringTypeService) {
        this.customerService = customerService;
        this.cateringTypeService = cateringTypeService;
    }

    @PostConstruct
    private synchronized void init() {

        List<CateringType> cateringTypes = new ArrayList<>();
        cateringTypes.add(CateringType.builder()
                .name("Standard1")
                .build());
        cateringTypes.add(CateringType.builder()
                .name("Standard2")
                .build());
        cateringTypes.add(CateringType.builder()
                .name("Standard3")
                .build());
        cateringTypes.add(CateringType.builder()
                .name("Sport1")
                .build());
        cateringTypes.add(CateringType.builder()
                .name("Sport2")
                .build());
        cateringTypes.add(CateringType.builder()
                .name("Sport3")
                .build());

        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder()
                .name("Zofia Wiśniewska")
                .address("Poniatowskiego Józefa 93A/87, Gdańsk")
                .cateringType(cateringTypes.get(0))
                .build());
        customers.add(Customer.builder()
                .name("Maurycy Dąbrowski")
                .address("Krasińskiego Zygmunta 87A/58, Gdańsk")
                .cateringType(cateringTypes.get(5))
                .build());
        customers.add(Customer.builder()
                .name("Grzegorz Nowakowski")
                .address("Strażacka 66/94, Sopot")
                .cateringType(cateringTypes.get(1))
                .build());
        customers.add(Customer.builder()
                .name("Igor Ziółkowski")
                .address("Piłsudskiego Józefa 73A/93, Gdańsk")
                .cateringType(cateringTypes.get(2))
                .build());
        customers.add(Customer.builder()
                .name("Nikola Woźniak")
                .address("Szewska 47D, Gdańsk")
                .cateringType(cateringTypes.get(2))
                .build());
        customers.add(Customer.builder()
                .name("Gabriel Tomaszewski")
                .address("Modra 17A, Sopot")
                .cateringType(cateringTypes.get(5))
                .build());
        customers.add(Customer.builder()
                .name("Damian Borowski")
                .address("Topolowa 11C, Gdańsk")
                .cateringType(cateringTypes.get(5))
                .build());
        customers.add(Customer.builder()
                .name("Iga Dudek")
                .address("Kaszubska 91/27, Kowale")
                .cateringType(cateringTypes.get(3))
                .build());
        customers.add(Customer.builder()
                .name("Alicja Adamczyk")
                .address("Sadowa 32/48, Sopot")
                .cateringType(cateringTypes.get(4))
                .build());
        customers.add(Customer.builder()
                .name("Artur Malinowski")
                .address("Szkolna 39, Gdańsk")
                .cateringType(cateringTypes.get(1))
                .build());

        cateringTypes.forEach(cateringTypeService::create);
        customers.forEach(customerService::create);
    }
}
