package com.example.demo.service;

import com.example.demo.domain.Price;
import com.example.demo.file.DataParser;
import com.example.demo.formmat.OutputFormatter;
import com.example.demo.properties.FileProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;

    @Mock
    private DataParser dataParser;

    @Mock
    private OutputFormatter outputFormatter;

    @Mock
    private FileProperties fileProperties;

    @BeforeEach
    public void setUp() {
        priceService.setPriceList(
                List.of(
                        new Price(1, "서울", "가정용", 1, 1, 20, 690, ""),
                        new Price(2, "부산", "산업용", 2, 21, 30, 1090, "")
                )
        );
    }

    @Test
    @DisplayName("get cities")
    void testGetCities() {
        String cities = priceService.getCities();
        assertNotNull(cities);
        assertEquals("[서울, 부산]", cities);
    }

    @Test
    @DisplayName("get sectors")
    void testGetSectors() {
        String sectors = priceService.getSectors("서울");
        assertNotNull(sectors);
        assertEquals("[가정용]", sectors);
    }

    @Test
    @DisplayName("get unitPrice by city, sector")
    void testGetUnitPriceByCityAndSector() {
        String unitPrice = priceService.getUnitPriceByCityAndSector("서울", "가정용");
        assertNotNull(unitPrice);
        assertEquals("Price(id=1, city=서울, sector=가정용, unitPrice=690)", unitPrice);
    }

    @Test
    @DisplayName("get bill total")
    void testBillTotalOutput() {
        Price mockPrice = new Price(1, "서울", "가정용", 1, 1, 20, 690, "");
        when(outputFormatter.format(mockPrice, 10)).thenReturn("Formatted output");

        String billOutput = priceService.billTotalOutput("서울", "가정용", 10);
        assertNotNull(billOutput);
        assertEquals("Formatted output", billOutput);
    }

    @Test
    @DisplayName("exception")
    void testGetUnitPriceByCityAndSector_NotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            priceService.getUnitPriceByCityAndSector("인천", "가정용");
        });
    }

    @Test
    @DisplayName("exception")
    void testBillTotalOutput_NotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            priceService.billTotalOutput("인천", "가정용", 10);
        });
    }
}
