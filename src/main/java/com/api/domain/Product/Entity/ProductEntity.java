package com.api.domain.Product.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    /**
     *  @OneToMany : 부모 엔터티가 자식 엔티티를 가짐 상품은 하나 이미지는 여러개일 수 있어서 1 : N 관계
     *  mappedBy = "productEntity" : 자식 엔티티에 productEntity가 매핑된걸 나타냄
     *  cascade = CascadeType.REMOVE : 부모 엔티티를 삭제하면 자식 엔티티도 자동으로 삭제
     *  orphanRemoval = true : 부모 엔티티에서 자식 엔티티를 리스트에서 제거하면, 해당 자식 엔티티는 데이터베이스에서도 삭제
     */
    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ImageData> imageDataList = new ArrayList<>();

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    @Range(min = 999, max = 1000000)
    private int price;

    @NotNull(message = "갯수는 필수 입력 값입니다.")
    @Max(9999)
    private int quantity;

    @Builder
    public ProductEntity(int productId, String name, int price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductEntity() {}
}
