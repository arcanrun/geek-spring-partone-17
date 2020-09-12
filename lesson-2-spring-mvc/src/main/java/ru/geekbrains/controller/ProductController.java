package ru.geekbrains.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepostitory;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductRepostitory productRepostitory;

    @Autowired
    public void setProductRepostitory(ProductRepostitory productRepostitory) {
        this.productRepostitory = productRepostitory;
    }

    @GetMapping
    public String getAllProducts(Model model, @RequestParam(required = false) BigDecimal minPrice, @RequestParam(required = false) BigDecimal maxPrice) {
        List<Product> productList;
        if(minPrice != null && maxPrice == null){
            productList = productRepostitory.findByPriceGreaterThanEqual(minPrice);
        }
        else if(minPrice == null && maxPrice != null){
            productList = productRepostitory.findByPriceLessThanEqual(maxPrice);
        }

        else if(minPrice != null && maxPrice != null){
            productList = productRepostitory.findByPriceBetween(minPrice, maxPrice);
        }
        else {
            productList = productRepostitory.findAll();
        }

        model.addAttribute("productList", productList);
        return "all_products";
    }

    @GetMapping("/new")
    public String addNewProductsForm(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping("/new")
    public String addNewProduct(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(0)) <= 0) {
            return "redirect:/products";

        }
        productRepostitory.save(product);
        return "redirect:/products";
    }


}
