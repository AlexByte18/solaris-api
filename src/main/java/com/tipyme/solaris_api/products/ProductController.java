package com.tipyme.solaris_api.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
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

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> show(
        @PathVariable Long id
    ) {
        logger.info("Trying to get info of product with id " + id);
        Product product = productService.findById(id);
        ProductResponseDto productResponseDto = productMapper.toResponseDto(product);

        return ResponseEntity.ok(productResponseDto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable Long id
    ) {
        logger.info("Trying to delete product with id " + id);
        productService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
