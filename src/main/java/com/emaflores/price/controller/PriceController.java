package com.emaflores.price.controller;

import com.emaflores.price.dto.PriceDTO;
import com.emaflores.price.entity.Price;
import com.emaflores.price.service.PriceMapper;
import com.emaflores.price.service.PriceService;
import com.emaflores.price.validation.Validation;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@RestController
public class PriceController {

    private final PriceMapper priceMapper;
    private final PriceService priceService;
    private final Validation validation;

    @Autowired
    public PriceController(PriceMapper priceMapper, PriceService priceService, Validation validation) {
        this.priceMapper = priceMapper;
        this.priceService = priceService;
        this.validation = validation;
    }
    @GetMapping("/getPrice")
    public ResponseEntity<PriceDTO> getPrice(
            @RequestParam String date,
            @RequestParam @Pattern(regexp = "^[0-9]+$", message = "El productId debe ser un número.") String productId,
            @RequestParam @Pattern(regexp = "^[0-9]+$", message = "El brandId debe ser un número.") String brandId) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

            if (!validation.isValidProductId(Long.parseLong(productId))) {
                throw new IllegalArgumentException("El productId no es válido.");
            }

            if (!validation.isValidBrandId(Long.parseLong(brandId))) {
                throw new IllegalArgumentException("El brandId no es válido.");
            }

            Optional<Price> price = priceService.findBestMatchingPrice(dateTime, dateTime, Long.parseLong(productId), Long.parseLong(brandId));

            if (price.isPresent()) {
                PriceDTO priceDTO = priceMapper.mapToDTO(price.get());
                return ResponseEntity.ok(priceDTO);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron precios para los parámetros proporcionados.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha no es válida. El formato debe ser yyyy-MM-dd-HH.mm.ss");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Los valores de productId y brandId deben ser números enteros.");
        }
    }

}
