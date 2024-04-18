package org.javaacademy.carservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.carservice.dto.AdvertisementDto;
import org.javaacademy.carservice.entity.Advertisement;
import org.javaacademy.carservice.service.AdvertisementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/advertisement")
@RequiredArgsConstructor
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    @GetMapping
    public List<Advertisement> getAdvertisements() {
        return advertisementService.getAdvertisements();
    }

    @PostMapping
    public ResponseEntity createAdvertisement(@RequestBody AdvertisementDto body) {
        advertisementService.createAdvertisement(body);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/filter/date/{date}")
    public List<Advertisement> getAdvertisementsByDate(@PathVariable() LocalDate date) {
        return advertisementService.findAdvertisementsByDate(date);
    }

    @DeleteMapping
    public void deleteAdvertisementById(Integer id) {
        advertisementService.deleteAdvertisementById(id);
    }

    @GetMapping("/filter")
    public List<Advertisement> getAdvertisementByParams(@PathVariable(required = false) String brand,
                                                        @PathVariable(required = false) String color,
                                                        @PathVariable(required = false) BigDecimal price,
                                                        @PathVariable(required = false) String model) {

        long count = countParameters(brand, color, price, model);

        if (count == 1) {
            if (brand != null) {
                return advertisementService.findAdvertisementsByBrand(brand);
            } else if (color != null) {
                return advertisementService.findAdvertisementByColor(color);
            } else if (price != null) {
                return advertisementService.findAdvertisementByPrice(price);
            } else if (model != null) {
                return advertisementService.findAdvertisementByModel(model);
            }
        } else if (count >= 2) {
            return advertisementService.findAdvertisementByParams(brand, color, price, model);
        }

        return getAdvertisements();
    }

    @GetMapping("/id/{id}")
    public Advertisement getAdvertisementById(@PathVariable Integer id) {
        return advertisementService.findAdvertisementById(id);
    }

    private long countParameters(Object... parameters) {
        return Arrays.stream(parameters)
                .filter(Objects::nonNull)
                .count();
    }
}