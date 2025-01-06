package com.api.AOP;

import com.api.Product.ProductRepository;
import com.api.Product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ProductIdValidationAspect {

    private final ProductRepository productRepository;

    public ProductIdValidationAspect(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ValidateProductId 어노테이션이 붙은 productId 조회
    @Before("@annotation(com.api.AOP.CustomAnnotation.ValidateProductId) && args(productId, ..)")
    public void validateProductId(int productId) {
        boolean exists = productRepository.existsById(productId);
        if (!exists) {
            throw new IllegalArgumentException("Product with ID " + productId + " does not exist.");
        }
    }
}
