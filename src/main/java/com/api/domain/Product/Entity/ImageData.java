package com.api.domain.Product.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private int productId;

    /**
     *  @ManyToOne : N : 1 관계 상품은 하나 이미지는 여러개 있으니
     *  name = "product_id", referencedColumnName = "product_id" : 현재 product_id 컬럼하고 부모 엔티티 product_id 하고 매핑
     *  insertable = false, updatable = false : 이 엔티티에서는 product_id 값을 직접 관리하지 않음 데이터는 외부에서만 설정 가능
     */
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private ProductEntity productEntity;

    @Column
    private String originalName;

    @Column
    private String changeName;

    @Column
    private String path;

    public ImageData(){}

    @Builder
    public ImageData(Long id, int productId, ProductEntity productEntity, String originalName, String changeName, String path) {
        this.id = id;
        this.productId = productId;
        this.productEntity = productEntity;
        this.originalName = originalName;
        this.changeName = changeName;
        this.path = path;
    }
}
