package com.tipyme.solaris_api.products.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String code;
}
