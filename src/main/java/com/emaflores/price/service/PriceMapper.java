package com.emaflores.price.service;

import com.emaflores.price.dto.PriceDTO;
import com.emaflores.price.entity.Price;
import org.springframework.stereotype.Service;

@Service
public class PriceMapper {

    public PriceDTO mapToDTO(Price price) {
        PriceDTO dto = new PriceDTO();
        dto.setBrandId(price.getBrandId());
        dto.setProductId(price.getProductId());
        dto.setStartDate(price.getStartDate());
        dto.setEndDate(price.getEndDate());
        dto.setPriceList(price.getPriceList());
        dto.setPrice(price.getPrice());
        return dto;
    }
}

