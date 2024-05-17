package com.example.demo.service;

import com.example.demo.domain.Price;
import com.example.demo.file.DataParser;
import com.example.demo.formmat.OutputFormatter;
import com.example.demo.properties.FileProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PriceService {

    private final DataParser dataParser;
    private final OutputFormatter outputFormatter;
    private final FileProperties fileProperties;
    private List<Price> priceList;

    @PostConstruct
    public void initPrice() {
        priceList = dataParser.readPrice(fileProperties.getPriceFilePath());
    }

    public String billTotalOutput(String city, String sector, int usage) {
        return outputFormatter.format(getPriceByCityAndSectorAndUsage(city, sector, usage), usage);
    }

    public String getCities() {
        if(priceList.isEmpty()) {
            return "";
        }

        Set<String> cities = priceList.stream()
                .map(Price::getCity)
                .collect(Collectors.toCollection(HashSet::new));

        StringBuilder builder = new StringBuilder();
        builder.append("[");
        String joinedCities = String.join(", ", cities);
        builder.append(joinedCities);
        builder.append("]");

        return builder.toString();
    }

    public String getSectors(String city) {
        if(priceList.isEmpty()) {
            return "";
        }

        Set<String> sectors = priceList.stream()
                .filter(price -> price.getCity().equals(city))
                .map(Price::getSector)
                .collect(Collectors.toCollection(HashSet::new));

        StringBuilder builder = new StringBuilder();
        builder.append("[");
        String joinedSectors = String.join(", ", sectors);
        builder.append(joinedSectors);
        builder.append("]");

        return builder.toString();
    }

    public String getUnitPriceByCityAndSector(String city, String sector) {
        Price price = getPriceByCityAndSector(city, sector);

        if(price == null) {
            throw new IllegalArgumentException("일치하는 도시가 없습니다.");
        }

        return String.format("Price(id=%d, city=%s, sector=%s, unitPrice=%d", price.getId(), price.getCity(), price.getSector(), price.getUnitPrice());
    }

    private Price getPriceByCityAndSector(String city, String sector) {
        Optional<Price> priceOptional = priceList.stream()
                .filter(price -> price.getCity().equals(city) && price.getSector().equals(sector))
                .findFirst();

        return priceOptional.orElse(null);
    }

    private Price getPriceByCityAndSectorAndUsage(String city, String sector, int usage) {
        Optional<Price> priceOptional = priceList.stream()
                .filter(price -> price.getCity().equals(city) && price.getSector().equals(sector))
                .filter(price -> price.getStartSection() <= usage && price.getEndSection() >= usage)
                .findFirst();

        return priceOptional.orElse(null);
    }

}
