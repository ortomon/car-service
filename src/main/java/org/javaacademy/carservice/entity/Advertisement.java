package org.javaacademy.carservice.entity;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Advertisement {
//    @NonNull
//    private String id;
    @NonNull
    private Integer id;
    @NonNull
    private String brand;
    @NonNull
    private String color;
    @NonNull
    private BigDecimal price;
    @NonNull
    private String model;
    @NonNull
    private LocalDate date;
}