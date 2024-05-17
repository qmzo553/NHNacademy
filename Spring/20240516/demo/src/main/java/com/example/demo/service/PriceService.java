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

        Set<String> Cities = priceList.stream()
                .map(Price::getCity)
                .collect(Collectors.toCollection(HashSet::new));

        StringBuilder builder = new StringBuilder();
        builder.append("[");
        String joinedCities = String.join(", ", Cities);
        builder.append(joinedCities);
        builder.append("]");

        return builder.toString();
    }

    private Price getPriceByCityAndSectorAndUsage(String city, String sector, int usage) {
        Optional<Price> result = priceList.stream()
                .filter(price -> price.getCity().equals(city) && price.getSector().equals(sector))
                .filter(price -> price.getStartSection() <= usage && price.getEndSection() >= usage)
                .findFirst();

        return result.orElse(null);
    }

}
