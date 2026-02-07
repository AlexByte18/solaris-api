package com.tipyme.solaris_api.shared.validation;

import org.springframework.stereotype.Component;

import com.tipyme.solaris_api.products.repository.ProductRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UniqueProductCodeValidator implements ConstraintValidator<UniqueProductCode, String> {
    
    private final ProductRepository productRepository;

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        if (code == null || code.trim().isEmpty()) {
            return true; // @NotBlank se encarga de validar esto
        }
        return !productRepository.existsByCode(code);
    }
}
