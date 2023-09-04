package com.emaflores.price.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaflores.price.entity.Price;
import com.emaflores.price.repository.PriceRepository;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> findBestMatchingPrice(
            LocalDateTime startDate, LocalDateTime endDate, Long productId, Long brandId) {

        List<Price> matchingPrices = findPricesInDateRange(startDate, endDate, productId, brandId);

        if (!matchingPrices.isEmpty()) {
            return Optional.of(findPriceWithHighestPriority(matchingPrices));
        } else {
            return Optional.empty();
        }
    }

    private List<Price> findPricesInDateRange(
            LocalDateTime startDate, LocalDateTime endDate, Long productId, Long brandId) {
        return priceRepository
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandId(
                        startDate, endDate, productId, brandId);
    }

    private Price findPriceWithHighestPriority(List<Price> prices) {
        return prices.stream()
                .sorted(Comparator.comparing(Price::getPriority)
                        .thenComparing(Price::getStartDate).reversed())
                .findFirst()
                .orElse(null);
    }
}

