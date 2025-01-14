package com.api.domain.Product.Controller;

import com.api.global.AOP.CustomAnnotation.ValidateProductId;
import com.api.domain.Product.Dto.ProductDto;
import com.api.domain.Product.Service.ProductService;
import com.api.domain.Product.Repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @ValidateProductId
    @ResponseBody
    @GetMapping(value="/product/{productId}", produces="application/json; charset=UTF-8")
    public ResponseEntity<?> getProduct(@PathVariable int productId){
        /**
         *  Optional은 데이터를 안전하게 다루기 위한 래퍼일 뿐, 실제 데이터를 담고 있지 않습니다.
         *  get() 메서드는 Optional이 비어 있으면 NoSuchElementException을 던집니다.
         *  반드시 isPresent()로 존재 여부를 확인한 뒤 호출해야 안전합니다.
         *  Optional은 데이터가 없을 경우 null 대신 Optional.empty()를 반환하여 안전하게 처리 가능.
         */
//        Optional<ProductDto> product = productService.selectProduct(productId);
//
//        if (product.isPresent()) {
//            return ResponseEntity.ok(product.get());
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Product not found");
//        }

        // TODO : 위에 방식은 직접 처리 밑에 방식은 예외를 던져 전역적으로 처리 하는 방식

        // AOP로 검증을 하지만 한번더 없는 상품이 나오면 예외를 던짐
        ProductDto product = productService.selectProduct(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return ResponseEntity.ok(product);
    }

    @ValidateProductId
    @ResponseBody
    @DeleteMapping(value="/product/{productId}", produces="application/json; charset=UTF-8")
    public ResponseEntity<?> deleteProduct(@PathVariable int productId){
        try {
            productRepository.deleteById(productId);
            return ResponseEntity.ok("삭제 성공");
        } catch (DataIntegrityViolationException ex) {
            // catch 예외를 받아서 다시 예외를 던짐
            throw new DataIntegrityViolationException("database constraints");
        }
    }

    @ValidateProductId
    @ResponseBody
    @PatchMapping(value="/product/{productId}", produces="application/json; charset=UTF-8")
    public ResponseEntity<?> updateProduct(@PathVariable int productId, @Valid
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer price,
                              @RequestParam(required = false) Integer quantity){
        ProductDto res = productService.updateProduct(productId, name, price, quantity);
        log.info("res = {}", res);
        return ResponseEntity.ok(res);
    }

    // 파일 업로드 하나 만들어 보자 !
    @ResponseBody
    @PostMapping(value="/product/img/{productId}", produces="application/json; charset=UTF-8")
    public void imgProduct(@RequestParam("imageFiles") List<MultipartFile> imageFiles, @PathVariable Integer productId){
        log.info("imageFiles = {}", imageFiles);
        productService.uploadImg(imageFiles, productId);
    }

}
