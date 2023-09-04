package com.emaflores.price.repository;

import com.emaflores.price.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandId(
            LocalDateTime date, LocalDateTime date2, Long productId, Long brandId);
}


