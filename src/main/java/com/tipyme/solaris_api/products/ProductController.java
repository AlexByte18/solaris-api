package com.tipyme.solaris_api.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tipyme.solaris_api.products.dto.ProductRequestDto;
import com.tipyme.solaris_api.products.dto.ProductResponseDto;
import com.tipyme.solaris_api.products.mapper.ProductMapper;
import com.tipyme.solaris_api.products.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    
    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> index (
        @RequestParam(required = false) String name,
        @PageableDefault(page = 0, size = 10, sort = "name") Pageable pageable
    ) {
        Page<ProductResponseDto> products = productService.findAll(name, pageable);

        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> store (
        @Valid @RequestBody ProductRequestDto productRequestDto
    ) {
        Product productToCreate = productMapper.toEntity(productRequestDto);
        Product product = productService.save(productToCreate);
        ProductResponseDto productResponseDto = productMapper.toResponseDto(product);

        return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
    }
    

}
