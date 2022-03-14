package cateringType.controller;

import cateringType.dto.CreateCateringTypeRequest;
import cateringType.dto.GetCateringTypeResponse;
import cateringType.dto.GetCateringTypesResponse;
import cateringType.entity.CateringType;
import cateringType.service.CateringTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;


//@CrossOrigin(origins = "http://localhost:8083/*", maxAge = 360000)
@RestController
@RequestMapping("api/cateringTypes")
public class CateringTypeController {

    private CateringTypeService cateringTypeService;

    @Autowired
    public CateringTypeController(CateringTypeService cateringTypeService) {
        this.cateringTypeService = cateringTypeService;
    }

    @GetMapping
    public ResponseEntity<GetCateringTypesResponse> getCateringTypes() {
        return ResponseEntity.ok(GetCateringTypesResponse.entityToDtoMapper().apply(cateringTypeService.findAll()));
    }

    /**
     * @param cateringTypeName cateringType's login
     * @return single cateringType in JSON format or 404 when cateringType does not exist
     */
    @GetMapping("{cateringTypeName}")
    public ResponseEntity<GetCateringTypeResponse> getCateringType(@PathVariable("cateringTypeName") String cateringTypeName) {
        return cateringTypeService.find(cateringTypeName)
                .map(value -> ResponseEntity.ok(GetCateringTypeResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
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
