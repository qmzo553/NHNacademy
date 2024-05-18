package com.example.demo.format;

import com.example.demo.domain.Price;
import com.example.demo.formmat.KoreanOutputFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KoreanOutputFormatterTest {

    private KoreanOutputFormatter formatter;

    @BeforeEach
    public void setUp() {
        formatter = new KoreanOutputFormatter();
    }

    @Test
    void testFormat() {
        Price price = new Price(1, "서울", "가정용", 1, 1, 20, 690, "");
        int usage = 10;

        String expectedOutput = "지자체명 : 서울, 업종 : 가정용, 구간금액(원) : 690, 총금액(원) : 6900";
        String actualOutput = formatter.format(price, usage);

        assertEquals(expectedOutput, actualOutput);
    }
}
