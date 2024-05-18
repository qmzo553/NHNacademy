package com.example.demo.formmat;

import com.example.demo.domain.Price;

public class KoreanOutputFormatter implements OutputFormatter {

    @Override
    public String format(Price price, int usage) {
        return String.format("지자체명 : %s, 업종 : %s, 구간금액(원) : %d, 총금액(원) : %d",
                price.getCity(),
                price.getSector(),
                price.getUnitPrice(),
                usage * price.getUnitPrice());
    }
}
