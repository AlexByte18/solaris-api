package com.tipyme.solaris_api.products.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tipyme.solaris_api.products.Product;
import com.tipyme.solaris_api.products.dto.ProductRequestDto;
import com.tipyme.solaris_api.products.dto.ProductResponseDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDto toResponseDto(Product product);
    List<ProductResponseDto> toProductResponseDtoList(List<Product> products);

    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductRequestDto productRequestDto);
}
