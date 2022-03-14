package lab3.cateringType.controller;

import lab3.cateringType.dto.CreateCateringTypeRequest;
import lab3.cateringType.entity.CateringType;
import lab3.cateringType.service.CateringTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/cateringTypes")
public class CateringTypeController {

    private CateringTypeService cateringTypeService;

    @Autowired
    public CateringTypeController(CateringTypeService cateringTypeService) {
        this.cateringTypeService = cateringTypeService;
    }

    @DeleteMapping("{cateringTypeName}")
    public ResponseEntity<Void> deleteCateringType(@PathVariable("cateringTypeName") String cateringTypeName) {
        Optional<CateringType> cateringType = cateringTypeService.find(cateringTypeName);
        if (cateringType.isPresent()) {
            cateringTypeService.delete(cateringType.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> createCateringType(@RequestBody CreateCateringTypeRequest request, UriComponentsBuilder builder) {
        CateringType cateringType = CreateCateringTypeRequest.dtoToEntityMapper().apply(request);
        cateringTypeService.create(cateringType);
        return ResponseEntity.created(builder.pathSegment("api", "cateringTypes", "{cateringTypeName}")
                .buildAndExpand(cateringType.getName()).toUri()).build();
    }

}
