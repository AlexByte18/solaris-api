package com.tipyme.solaris_api.products;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tipyme.solaris_api.products.dto.ProductResponseDto;
import com.tipyme.solaris_api.products.interfaces.ProductService;
import com.tipyme.solaris_api.products.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    
    @GetMapping
    public List<ProductResponseDto> index() {
        List<Product> products = productService.findAll();

        return productMapper.toProductResponseDtoList(products);
    }
    

}
