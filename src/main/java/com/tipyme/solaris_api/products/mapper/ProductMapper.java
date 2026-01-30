package com.tipyme.solaris_api.products.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.tipyme.solaris_api.products.Product;
import com.tipyme.solaris_api.products.dto.ProductResponseDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    List<ProductResponseDto> toProductResponseDtoList(List<Product> products);
    
}
