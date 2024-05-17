package com.example.demo.file;

import com.example.demo.domain.Account;
import com.example.demo.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonDataParserTest {

    private JsonDataParser jsonDataParser;
    private String accountFilePath = "src/test/resources/account.json";
    private String tariffFilePath = "src/test/resources/Tariff.json";

    @BeforeEach
    public void setUp() {
        jsonDataParser = new JsonDataParser();
    }

    @Test
    public void testReadAccount() {
        List<Account> accounts = jsonDataParser.readAccount(accountFilePath);

        assertNotNull(accounts);
        assertEquals(2, accounts.size());

        Account account1 = accounts.get(0);
        assertEquals("1", account1.getId());
        assertEquals("password1", account1.getPassword());
        assertEquals("John", account1.getName());

        Account account2 = accounts.get(1);
        assertEquals("2", account2.getId());
        assertEquals("password2", account2.getPassword());
        assertEquals("Jane", account2.getName());
    }

    @Test
    public void testReadPrice() {
        List<Price> prices = jsonDataParser.readPrice(tariffFilePath);

        assertNotNull(prices);
        assertEquals(2, prices.size());

        Price price1 = prices.getFirst();
        assertEquals(1, price1.getId());
        assertEquals("서울", price1.getCity());
        assertEquals("가정용", price1.getSector());
        assertEquals(1, price1.getStage());
        assertEquals(1, price1.getStartSection());
        assertEquals(20, price1.getEndSection());
        assertEquals(690, price1.getUnitPrice());
        assertEquals("", price1.getBasePrice());

        Price price2 = prices.get(1);
        assertEquals(2, price2.getId());
        assertEquals("부산", price2.getCity());
        assertEquals("산업용", price2.getSector());
        assertEquals(2, price2.getStage());
        assertEquals(21, price2.getStartSection());
        assertEquals(30, price2.getEndSection());
        assertEquals(1090, price2.getUnitPrice());
        assertEquals("", price2.getBasePrice());
    }
}
