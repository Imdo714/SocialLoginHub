package com.api.Product;

import com.api.Product.Dto.ProductDto;
import com.api.Product.Entity.ProductEntity;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @ResponseBody
    @PostMapping(value="/product", produces="application/json; charset=UTF-8")
    public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto productDto){
        ProductDto savedProduct = productService.productSave(productDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @ResponseBody
    @GetMapping(value="/product/{productId}", produces="application/json; charset=UTF-8")
    public ResponseEntity<?> getProduct(@PathVariable int productId){
        /**
         *  Optional은 데이터를 안전하게 다루기 위한 래퍼일 뿐, 실제 데이터를 담고 있지 않습니다.
         *  get() 메서드는 Optional이 비어 있으면 NoSuchElementException을 던집니다.
         *  반드시 isPresent()로 존재 여부를 확인한 뒤 호출해야 안전합니다.
         *  Optional은 데이터가 없을 경우 null 대신 Optional.empty()를 반환하여 안전하게 처리 가능.
         */
        Optional<ProductDto> product = productService.selectProduct(productId);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found");
        }
    }

    @ResponseBody
    @DeleteMapping(value="/product/{productId}", produces="application/json; charset=UTF-8")
    public ResponseEntity<?> deleteProduct(@PathVariable int productId){
        boolean exists = productRepository.existsById(productId);
        if (exists) {
            productRepository.deleteById(productId);
            return ResponseEntity.ok("삭제 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("없는 상품 ID : " + productId);
        }
    }

}
