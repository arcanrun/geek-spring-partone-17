package com.boot.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.boot.persist.entity.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepostitory extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    List<Product> findByPriceGreaterThanEqual(BigDecimal minPrice);

    List<Product> findByPriceLessThanEqual(BigDecimal maxPrice);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
