package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepostitory;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductRepostitory productRepostitory;

    @Autowired
    public void setProductRepostitory(ProductRepostitory productRepostitory) {
        this.productRepostitory = productRepostitory;
    }

    @Override
    public List<Product> findByPriceGreaterThanEqual(BigDecimal price) {
        return productRepostitory.findByPriceGreaterThanEqual(price);
    }

    @Override
    public List<Product> findByPriceLessThanEqual(BigDecimal prcie) {
        return productRepostitory.findByPriceLessThanEqual(prcie);
    }

    @Override
    public List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepostitory.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> findAll() {
        return productRepostitory.findAll();
    }

    @Override
    public void save(Product product) {
        productRepostitory.save(product);
    }
}
