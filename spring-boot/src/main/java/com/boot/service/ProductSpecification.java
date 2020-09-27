package com.boot.service;

import org.springframework.data.jpa.domain.Specification;
import com.boot.persist.entity.Product;

import java.math.BigDecimal;

public class ProductSpecification {
    static Specification<Product> priceGreaterThanOrEqual(BigDecimal minPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    static Specification<Product> priceLesserThanOrEqual(BigDecimal maxPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    static Specification<Product> priceInBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .between(root.get("price"), minPrice, maxPrice);
    }
}
