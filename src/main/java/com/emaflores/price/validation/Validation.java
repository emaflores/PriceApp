package com.emaflores.price.validation;

import org.springframework.stereotype.Component;

@Component
public class Validation {

    public boolean isValidProductId(Long productId) {
        return productId != null && productId > 0;
    }

    public boolean isValidBrandId(Long brandId) {
        return brandId != null && brandId > 0;
    }
}
