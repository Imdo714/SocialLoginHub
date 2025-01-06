package com.api.Product;

import com.api.Product.Dto.ProductDto;
import com.api.Product.Entity.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto productSave(ProductDto productDto){
//        ProductEntity product = ProductEntity.builder()
//                .name(productDto.getName())
//                .price(productDto.getPrice())
//                .quantity(productDto.getQuantity())
//                .build();
// TODO : Builder방식이냐 Setter방식이냐

        ProductEntity product = new ProductEntity();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());

        ProductEntity res = productRepository.save(product);

        ProductDto savedProduct = new ProductDto();
        savedProduct.setProductId(res.getProductId());
        savedProduct.setName(res.getName());
        savedProduct.setPrice(res.getPrice());
        savedProduct.setQuantity(res.getQuantity());

        return savedProduct;
    }

    public Optional<ProductDto> selectProduct(int productId){
        // TODO : Optional의 map 메서드를 사용하여 ProductEntity를 ProductDto로 변환합니다.
        return productRepository.findById(productId)
                .map(ProductDto::new);

//        return productRepository.findById(productId)
//                .map(product -> {
//                    // Entity → DTO 변환
//                    ProductDto dto = new ProductDto();
//                    dto.setProductId(product.getProductId());
//                    dto.setName(product.getName());
//                    dto.setPrice(product.getPrice());
//                    dto.setQuantity(product.getQuantity());
//                    return dto;
//                });
    }

    public ProductDto  updateProduct(int productId, String  name, Integer price, Integer quantity){
        log.info("aaaaa");
        ProductEntity existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (name != null) {
            existingProduct.setName(name);
        }
        if (price != null) {
            existingProduct.setPrice(price);
        }
        if (quantity != null) {
            existingProduct.setQuantity(quantity);
        }

        ProductEntity savedProduct = productRepository.save(existingProduct);
        return new ProductDto(savedProduct);
    }
}
