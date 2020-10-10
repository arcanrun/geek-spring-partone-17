package com.boot.controller.rest;

import com.boot.persist.entity.Product;
import com.boot.service.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductRestController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public Page<Product> getAllProducts(
            Model model,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize
    ) {
        return productService.findAll(minPrice, maxPrice, pageIndex, pageSize);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Integer id) throws NotFoundException {
        return productService
                .findById(id)
                .orElseThrow(
                        ()->new NotFoundException(
                                String.format("Product with id=%s is not found", id)
                        )
                );
    }


    @PutMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Product updateProduct(@RequestBody Product product) {
        productService.save(product);
        return product;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProduct(@RequestBody Product product){
        productService.save(product);
        return product;
    }


    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteById(id);

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandlingRest(NotFoundException ex){
        return ex.getLocalizedMessage();
    }


}
