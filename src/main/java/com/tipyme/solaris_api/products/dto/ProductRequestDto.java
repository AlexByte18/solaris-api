package com.tipyme.solaris_api.products.dto;

import com.tipyme.solaris_api.shared.validation.UniqueProductCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotBlank(message = "Code is mandatory")
    @UniqueProductCode(message = "Product code already exists")
    private String code;
}