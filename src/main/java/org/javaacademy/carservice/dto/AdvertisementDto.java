package org.javaacademy.carservice.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class AdvertisementDto {
    String brand;
    String color;
    BigDecimal price;
    String model;
    LocalDate date;
}
