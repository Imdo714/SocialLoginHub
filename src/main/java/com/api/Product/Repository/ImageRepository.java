package com.api.Product.Repository;

import com.api.Product.Entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageData, Long> {
}
