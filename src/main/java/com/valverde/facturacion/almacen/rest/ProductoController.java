package com.valverde.facturacion.almacen.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valverde.facturacion.almacen.converter.ProductoConverter;
import com.valverde.facturacion.almacen.dto.ProductoDto;
import com.valverde.facturacion.almacen.entity.Producto;
import com.valverde.facturacion.almacen.service.ProductoService;
import com.valverde.facturacion.almacen.util.WrapperResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;




@RestController
@RequestMapping("/v1/productos")
public class ProductoController {
@Autowired
    private ProductoService service;

    @Autowired
    private ProductoConverter converter;
    @GetMapping
    public ResponseEntity<List<ProductoDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<ProductoDto> producto = converter.fromEntities(service.findAll());
        return new WrapperResponse(true, "success", producto).createResponse(HttpStatus.OK);
    }



    
    @PostMapping
    public ResponseEntity<ProductoDto> create (@RequestBody ProductoDto producto) {
        Producto entity = converter.fromDTO(producto);
        ProductoDto dto = converter.fromEntity(service.save(entity));//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> update (@PathVariable("id") int id, @RequestBody ProductoDto producto) {
        Producto entity = converter.fromDTO(producto);
        ProductoDto dto = converter.fromEntity(service.save(entity));
//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable("id") int id) {
        service.delete(id);
//        return ResponseEntity.ok(null);
        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> findById (@PathVariable("id") int id) {
        ProductoDto dto = converter.fromEntity(service.findById(id));

//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }
}
