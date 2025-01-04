package com.api.Product;

import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ProductServiceTest {

    private ProductService productService;
    private ProductPort productPort;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        productPort = new ProductAdapter(productRepository);
        productService = new ProductService(this, productPort);
    }

    @Test
    void 상품등록(){
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        final AddProducttRquest request = new AddProducttRquest(name, price, discountPolicy);
        productService.addProduct(request);
    }

    private enum DiscountPolicy {
        NONE
    }

    class ProductService {
        private final ProductServiceTest productServiceTest;
        private final ProductServiceTest.ProductPort productPort;

        ProductService(ProductServiceTest productServiceTest, final ProductServiceTest.ProductPort productPort) {
            this.productServiceTest = productServiceTest;
            this.productPort = productPort;
        }

        public void addProduct(ProductServiceTest.AddProducttRquest request) {
//            throw new UnsupportedOperationException("Unsupported addProduct");
            final ProductServiceTest.Product product = new ProductServiceTest.Product(request.name(), request.price(), request.discountPolicy());
            productPort.save(product);
        }
    }


    class ProductAdapter implements ProductPort {
        private final ProductRepository productRepository;

        ProductAdapter(final ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        @Override
        public void save(Product product) {
            productRepository.save(product);
        }
    }

    record AddProducttRquest(String name, int price, DiscountPolicy discountPolicy) {
        public AddProducttRquest(String name, int price, DiscountPolicy discountPolicy) {
            Assert.hasText(name, " 상품명은 필수입니다.");
            Assert.isTrue(price > 0, "상품 가격은 0보다 커야함");
            Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
        }
    }

    class Product {
        private Long id;
        private final String name;
        private final int price;
        private final ProductServiceTest.DiscountPolicy discountPolicy;

        public Product(String name, int price, ProductServiceTest.DiscountPolicy discountPolicy) {
            Assert.hasText(name, " 상품명은 필수입니다.");
            Assert.isTrue(price > 0, "상품 가격은 0보다 커야함");
            Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
        }

        public void assignId(final Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

    interface ProductPort {
        void save(Product product);
    }

    class ProductRepository {
        private Long sequence = 0L;
        private Map<Long, Product> persistance = new HashMap<>();

        public void save(Product product) {
            product.assignId(++sequence);
            persistance.put(product.getId(), product);
        }
    }

}
