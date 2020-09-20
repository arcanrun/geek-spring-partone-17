package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepostitory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
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

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepostitory.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        productRepostitory.deleteById(id);
    }

    @Override
    public List<Product> findAll(BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> spec = Specification.where(null);

        if (minPrice != null) {
            assert spec != null;
            spec = spec.and(ProductSpecification.priceGreaterThanOrEqual(minPrice));
        }

        if (maxPrice != null) {
            assert spec != null;
            spec = spec.and(ProductSpecification.priceLesserThanOrEqual(maxPrice));
        }

        return productRepostitory.findAll(spec);
    }


}
