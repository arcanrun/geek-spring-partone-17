package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.service.ProductService;

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
        System.out.println("pageSize===>" + pageSize);
        Page<Product> productPage = productService.findAll(minPrice, maxPrice, pageIndex, pageSize);
        model.addAttribute("productList", productPage);
        return "all_products";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model) {
        Optional<Product> productOptional = productService.findById(id);

        if (!productOptional.isPresent()) {
            return "redirect:/products";
        }

        Product product = productOptional.get();
        model.addAttribute("product", product);

        return "product_form";
    }


    @GetMapping("/new")
    public String addNewProductsForm(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping("/update")
    public String addNewProduct(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(0)) <= 0) {
            return "redirect:/products";
        }
        productService.save(product);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteById(id);
        return "redirect:/products";
    }


}
