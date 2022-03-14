package cateringType.event.repository;

import cateringType.entity.CateringType;
import cateringType.event.dto.CreateCateringTypeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class CateringTypeEventRepository  {

    private RestTemplate restTemplate;

    @Autowired
    public CateringTypeEventRepository(@Value("${lab3.customers.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(CateringType cateringType) {
        restTemplate.delete("/cateringTypes/{cateringTypeName}", cateringType.getName());
    }

    public void create(CateringType cateringType) {
        restTemplate.postForLocation("/cateringTypes", CreateCateringTypeRequest.entityToDtoMapper().apply(cateringType));
    }
}
