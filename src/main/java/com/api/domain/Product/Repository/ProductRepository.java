package com.api.domain.Product.Repository;

import com.api.domain.Product.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
