package ru.geekbrains.service;

import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface ProductService {

    public List<Product>  findByPriceGreaterThanEqual(BigDecimal price);

    public List<Product> findByPriceLessThanEqual(BigDecimal prcie);

    public List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    public List<Product> findAll();

    public void save(Product product);

    Optional<Product> findById(Integer id);

    public void deleteById(Integer id);
}
