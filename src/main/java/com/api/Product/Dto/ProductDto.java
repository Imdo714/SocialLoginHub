package com.api.Product.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ProductDto {

    private int productId;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    @Range(min = 999, max = 1000000)
    private int price;

    @NotNull(message = "갯수는 필수 입력 값입니다.")
    @Max(9999)
    private int quantity;

    public ProductDto(){};

    public ProductDto(int productId, String name, int price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
