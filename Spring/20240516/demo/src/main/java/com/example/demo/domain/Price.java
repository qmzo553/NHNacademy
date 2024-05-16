package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Price {

    @JsonProperty("순번")
    private long id;

    @JsonProperty("지자체명")
    private String city;

    @JsonProperty("업종")
    private String sector;

    @JsonProperty("단계")
    private int stage;

    @JsonProperty("구간시작(세제곱미터)")
    private int startSection;

    @JsonProperty("구간끝(세제곱미터)")
    private int endSection;

    @JsonProperty("구간금액(원)")
    private int sectionPrice;

    @JsonProperty("단계별 기본요금(원)")
    private String basePrice;

    public Price() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return id == price.id
                && city.equals(price.city)
                && sector.equals(price.sector)
                && stage == price.stage
                && startSection == price.startSection
                && endSection == price.endSection
                && sectionPrice == price.sectionPrice
                && basePrice.equals(price.basePrice);
    }

    @Override
    public int hashCode() { return Objects.hash(id, city, sector, stage, startSection, endSection, sectionPrice); }

    @Override
    public String toString() {
        return "Price(id=" + id + ", city=" + city + ", sector=" + sector + ", unitPrice=" + (endSection - startSection) * sectionPrice + ")";
    }
}
