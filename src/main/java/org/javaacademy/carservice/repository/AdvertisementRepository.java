package org.javaacademy.carservice.repository;

import lombok.Getter;
import org.javaacademy.carservice.entity.Advertisement;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@Getter
public class AdvertisementRepository {
    private final List<Advertisement> advertisements = new ArrayList<>();

    public void add(Advertisement advertisement) {
        advertisements.add(advertisement);
    }

    public List<Advertisement> findByDate(LocalDate date) {
        return filterAdvertisements(advertisement -> advertisement.getDate().equals(date));
    }

    public void deleteById(Integer id) {
        advertisements.removeIf(advertisement -> advertisement.getId().equals(id));
    }

    public Advertisement findById(Integer id) {
        return advertisements.stream()
                .filter(advertisement -> advertisement.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Advertisement> findByBrand(String brand) {
        return filterAdvertisements(advertisement -> advertisement.getBrand().equals(brand));
    }

    public List<Advertisement> findByColor(String color) {
        return filterAdvertisements(advertisement -> advertisement.getColor().equals(color));
    }

    public List<Advertisement> findByPrice(BigDecimal price) {
        return filterAdvertisements(advertisement -> advertisement.getPrice().compareTo(price) == 0);
    }

    public List<Advertisement> findByModel(String model) {
        return filterAdvertisements(advertisement -> advertisement.getModel().equals(model));
    }

    public List<Advertisement> findByParams(String brand, String color, BigDecimal price, String model) {
        return filterAdvertisements(advertisement ->
                (brand == null || advertisement.getBrand().equals(brand))
                        && (color == null || advertisement.getColor().equals(color))
                        && (price == null || advertisement.getPrice().compareTo(price) == 0)
                        && (model == null || advertisement.getModel().equals(model))
        );
    }

    private List<Advertisement> filterAdvertisements(Predicate<Advertisement> predicate) {
        return advertisements.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}