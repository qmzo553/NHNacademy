package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private int unitPrice;

    @JsonProperty("단계별 기본요금(원)")
    private String basePrice;

    @JsonSetter("지자체명")
    public void setCity(String city) {
        this.city = city != null ? city.trim() : null;
    }

    @JsonSetter("업종")
    public void setSector(String sector) {
        this.sector = sector != null ? sector.trim() : null;
    }

    public long getBillTotal(int usage) {
        return (long) usage * unitPrice;
    }

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
                && unitPrice == price.unitPrice
                && basePrice.equals(price.basePrice);
    }

    @Override
    public int hashCode() { return Objects.hash(id, city, sector, stage, startSection, endSection, unitPrice); }

    @Override
    public String toString() {
        return "Price(id=" + id +
                ", city=" + city +
                ", sector=" + sector +
                ", stage=" + stage +
                ", startSection=" + startSection +
                ", endSection=" + endSection +
                ", unitPrice=" + unitPrice +
                ", basePrice=" + basePrice +
                ")";
    }
}
