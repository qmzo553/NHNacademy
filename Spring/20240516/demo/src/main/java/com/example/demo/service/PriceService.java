package com.example.demo.service;

import com.example.demo.domain.Price;
import com.example.demo.file.DataParser;
import com.example.demo.formmat.OutputFormatter;
import com.example.demo.properties.FileProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    private Price getPriceByCityAndSectorAndUsage(String city, String sector, int usage) {
//        Optional<Price> result = priceList.stream()
//                .filter(price -> price.getCity().equals(city) && price.getSector().equals(sector))
//                .filter(price -> price.getStartSection() <= usage && price.getEndSection() >= usage)
//                .findFirst();
//
//        return result.orElse(null);
        return new Price(1, "동두천시", "가정용", 1, 1, 20, 690, "");
    }

}
