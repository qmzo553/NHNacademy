package com.example.demo.format;

import com.example.demo.domain.Price;
import com.example.demo.formmat.EnglishOutputFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnglishOutputFormatterTest {

    private EnglishOutputFormatter formatter;

    @BeforeEach
    public void setUp() {
        formatter = new EnglishOutputFormatter();
    }

    @Test
    void testFormat() {
        Price price = new Price(1, "서울", "가정용", 1, 1, 20, 690, "");
        int usage = 15;

        String expectedOutput = "city : 서울, sector : 가정용, price(won) : 690, bill total(won) : 10350";
        String actualOutput = formatter.format(price, usage);

        assertEquals(expectedOutput, actualOutput);
    }
}
