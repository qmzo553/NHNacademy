package com.example.demo.file;

import com.example.demo.domain.Account;
import com.example.demo.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CsvDataParserTest {

    private CsvDataParser csvDataParser;
    private final String accountFilePath = "src/test/resources/account.csv";
    private final String tariffFilePath = "src/test/resources/Tariff.csv";

    @BeforeEach
    public void setUp() {
        csvDataParser = new CsvDataParser();
    }

    @Test
    @DisplayName("read account")
    public void testReadAccount() {
        List<Account> accounts = csvDataParser.readAccount(accountFilePath);

        assertNotNull(accounts);
        assertEquals(2, accounts.size());

        Account account1 = accounts.get(0);
        assertEquals("1", account1.getId());
        assertEquals("password1", account1.getPassword().trim());
        assertEquals("John", account1.getName().trim());

        Account account2 = accounts.get(1);
        assertEquals("2", account2.getId());
        assertEquals("password2", account2.getPassword().trim());
        assertEquals("Jane", account2.getName().trim());
    }

    @Test
    @DisplayName("read price")
    public void testReadPrice() {
        List<Price> prices = csvDataParser.readPrice(tariffFilePath);

        assertNotNull(prices);
        assertEquals(2, prices.size());

        Price price1 = prices.get(0);
        assertEquals(1, price1.getId());
        assertEquals("서울", price1.getCity().trim());
        assertEquals("가정용", price1.getSector().trim());
        assertEquals(1, price1.getStage());
        assertEquals(1, price1.getStartSection());
        assertEquals(20, price1.getEndSection());
        assertEquals(690, price1.getUnitPrice());
        assertEquals("", price1.getBasePrice().trim());

        Price price2 = prices.get(1);
        assertEquals(2, price2.getId());
        assertEquals("부산", price2.getCity().trim());
        assertEquals("산업용", price2.getSector().trim());
        assertEquals(2, price2.getStage());
        assertEquals(21, price2.getStartSection());
        assertEquals(30, price2.getEndSection());
        assertEquals(1090, price2.getUnitPrice());
        assertEquals("", price2.getBasePrice().trim());
    }
}
