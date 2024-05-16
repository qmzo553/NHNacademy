package com.example.demo.formmat;

import com.example.demo.domain.Price;

public class EnglishOutputFormatter implements OutputFormatter {

    @Override
    public String format(Price price, int usage) {
        return String.format("city : %s, sector : %s, price(won) : %d, bill total(won) : %d",
                price.getCity(),
                price.getSector(),
                price.getUnitPrice(),
                usage * price.getUnitPrice());
    }
}
