package org.javaacademy.carservice.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.carservice.dto.AdvertisementDto;
import org.javaacademy.carservice.entity.Advertisement;
import org.javaacademy.carservice.repository.AdvertisementRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;

    public void createAdvertisement(AdvertisementDto dto) {
        String id = UUID.randomUUID().toString();
        Advertisement advertisement = new Advertisement(
                advertisementRepository.getAdvertisements().size(),
                dto.getBrand(),
                dto.getColor(),
                dto.getPrice(),
                dto.getModel(),
                dto.getDate());
        advertisementRepository.add(advertisement);
    }

    public List<Advertisement> findAdvertisementsByDate(LocalDate date) {
        return advertisementRepository.findByDate(date);
    }

    public void deleteAdvertisementById(Integer id) {
        advertisementRepository.deleteById(id);
    }

    public Advertisement findAdvertisementById(Integer id) {
        return advertisementRepository.findById(id);
    }

    public List<Advertisement> getAdvertisements() {
        return advertisementRepository.getAdvertisements();
    }

    public List<Advertisement> findAdvertisementsByBrand(String brand) {
        return advertisementRepository.findByBrand(brand);
    }

    public List<Advertisement> findAdvertisementByColor(String color) {
        return advertisementRepository.findByColor(color);
    }

    public List<Advertisement> findAdvertisementByPrice(BigDecimal price) {
        return advertisementRepository.findByPrice(price);
    }

    public List<Advertisement> findAdvertisementByModel(String model) {
        return advertisementRepository.findByModel(model);
    }

    public List<Advertisement> findAdvertisementByParams(String brand, String color, BigDecimal price, String model) {
        return advertisementRepository.findByParams(brand, color, price, model);
    }
}