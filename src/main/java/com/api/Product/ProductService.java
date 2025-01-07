package com.api.Product;

import com.api.Product.Entity.ImageData;
import com.api.Product.Dto.ImageHandler;
import com.api.Product.Dto.ProductDto;
import com.api.Product.Entity.ProductEntity;
import com.api.Product.Repository.ImageRepository;
import com.api.Product.Repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public ProductService(ProductRepository productRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

    public ProductDto productSave(ProductDto productDto){
        ProductEntity product = ProductEntity.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();

        ProductEntity savedProduct = productRepository.save(product);
//
//        ProductDto savedProduct = new ProductDto();
//        savedProduct.setProductId(res.getProductId());
//        savedProduct.setName(res.getName());
//        savedProduct.setPrice(res.getPrice());
//        savedProduct.setQuantity(res.getQuantity());

        return new ProductDto(savedProduct);
    }

    public Optional<ProductDto> selectProduct(int productId){
        // Optional의 map 메서드를 사용하여 ProductEntity를 ProductDto로 변환합니다.
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

    public void uploadImg(List<MultipartFile> images, Integer productId){
        ImageHandler imageHandler = new ImageHandler();

        for (MultipartFile image : images) {
            String imgPath = imageHandler.imgSave(image);
            String changeName = imageHandler.createStoreFileName(image.getOriginalFilename());

            ImageData imageData = ImageData.builder()
                    .originalName(image.getOriginalFilename())
                    .changeName(changeName)
                    .productId(productId)
                    .path(imgPath)
                    .build();

            ImageData res = imageRepository.save(imageData);
            log.info("res = {}", res);
        }
    }

}
