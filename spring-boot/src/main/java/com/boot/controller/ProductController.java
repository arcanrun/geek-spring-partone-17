package com.boot.controller;

import com.boot.controller.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.boot.persist.entity.Product;
import com.boot.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(
            Model model,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize
    ) {
        Page<Product> productPage = productService.findAll(minPrice, maxPrice, pageIndex, pageSize);
        model.addAttribute("productList", productPage);
        return "all_products";
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public String editProduct(@PathVariable("id") Integer id, Model model) throws ResourceNotFoundException {
        Optional<Product> productOptional = productService.findById(id);

//        if (!productOptional.isPresent()) {
//            return "redirect:/products";
//        }

        Product product = productOptional.orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("product", product);

        return "product_form";
    }


    @GetMapping("/new")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public String addNewProductsForm(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping("/update")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public String addNewProduct(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(0)) <= 0) {
            return "redirect:/products";
        }
        productService.save(product);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}/delete")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteById(id);
        return "redirect:/products";
    }


}
