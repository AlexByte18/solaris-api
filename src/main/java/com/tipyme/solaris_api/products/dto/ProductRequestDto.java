package com.tipyme.solaris_api.products.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private Double price;

    @NotBlank
    private String code;
}