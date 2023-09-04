package com.emaflores.price.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceDTO {
    private Long brandId;
    private Long productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceList;
    private Double price;
}

