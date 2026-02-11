package com.BlueAndPurple.products.controller;

import com.BlueAndPurple.products.model.Products;
import com.BlueAndPurple.products.service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("products")

public class ProductsController {
    private final ProductsService service;

    public ProductsController(ProductsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Products> saveProducts(@RequestBody Products product){
        service.saveProducts(product);
        System.out.println(">>>> Recebido no Controller: " + product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<Products> getByCode(@PathVariable("codigo")Long codigo){
        return service
                .getByCode(codigo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());


    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<Void> delete(@PathVariable("codigo") Long codigo){
        var products = service.getByCode(codigo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "product does not exist."
                ));

        service.delete(products);
        return ResponseEntity.noContent().build();

    }
}
