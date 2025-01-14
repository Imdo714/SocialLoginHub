package com.api.domain.Product.Repository;

import com.api.domain.Product.Entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageData, Long> {
}
