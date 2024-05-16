package com.example.demo.service;

import com.example.demo.domain.Price;
import com.example.demo.file.DataParser;
import com.example.demo.formmat.OutputFormatter;
import com.example.demo.properties.FileProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public String billTotalOutput(String city, String sector, int useAmount) {
        priceList.stream()
                .filter(price -> price.getCity().equals(city) && price.getSector().equals(sector))
                .collect(Collectors.toList());

        return outputFormatter.format(null);
    }

    private Price getPriceByCityAndSector(String city, String sector) {

        return null;
    }

}
