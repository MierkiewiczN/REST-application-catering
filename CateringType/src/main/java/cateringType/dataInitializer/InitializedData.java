package cateringType.dataInitializer;

import cateringType.entity.CateringType;
import cateringType.service.CateringTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class InitializedData {


    private final CateringTypeService cateringTypeService;


    @Autowired
    public InitializedData(CateringTypeService cateringTypeService) {
        this.cateringTypeService = cateringTypeService;
    }

    @PostConstruct
    private synchronized void init() {
        List<CateringType> cateringTypes = new ArrayList<>();
        cateringTypes.add(CateringType.builder()
                .name("Standard1")
                .calories(1800)
                .price(1499.70).build());
        cateringTypes.add(CateringType.builder()
                .name("Standard2")
                .calories(2200)
                .price(1649.70).build());
        cateringTypes.add(CateringType.builder()
                .name("Standard3")
                .calories(2500)
                .price(1799.70).build());
        cateringTypes.add(CateringType.builder()
                .name("Sport1")
                .calories(2300)
                .price(1649.70).build());
        cateringTypes.add(CateringType.builder()
                .name("Sport2")
                .calories(2600)
                .price(1799.70).build());
        cateringTypes.add(CateringType.builder()
                .name("Sport3")
                .calories(3000)
                .price(1964.70).build());


        cateringTypes.forEach(cateringTypeService::create);
    }

}
