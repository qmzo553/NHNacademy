package com.example.demo.command;

import com.example.demo.domain.Account;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyCommandsTest {

    @InjectMocks
    private MyCommands myCommands;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private PriceService priceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("login")
    void testLogin() {
        when(authenticationService.login(anyString(), anyString())).thenReturn(new Account("1", "password1", "John"));

        String result = myCommands.login("1", "password1");

        assertEquals("Account(id=1, password=password1, name=John)", result);
        verify(authenticationService, times(1)).login("1", "password1");
    }

    @Test
    @DisplayName("logout")
    void testLogout() {
        when(authenticationService.logout()).thenReturn("John good bye");

        String result = myCommands.logout();

        assertEquals("John good bye", result);
        verify(authenticationService, times(1)).logout();
    }

    @Test
    @DisplayName("current user")
    void testCurrentUser() {
        when(authenticationService.getCurrentLoginList()).thenReturn("Account list");

        String result = myCommands.currentUser();

        assertEquals("Account list", result);
        verify(authenticationService, times(1)).getCurrentLoginList();
    }

    @Test
    @DisplayName("city")
    void testCity() {
        when(priceService.getCities()).thenReturn("[서울, 부산]");

        String result = myCommands.city();

        assertEquals("[서울, 부산]", result);
        verify(priceService, times(1)).getCities();
    }

    @Test
    @DisplayName("sector A")
    void testSector() {
        when(priceService.getSectors("서울")).thenReturn("[가정용, 산업용]");

        String result = myCommands.sector("서울");

        assertEquals("[가정용, 산업용]", result);
        verify(priceService, times(1)).getSectors("서울");
    }

    @Test
    @DisplayName("price A B")
    void testPrice() {
        when(priceService.getUnitPriceByCityAndSector("서울", "가정용")).thenReturn("Price(id=1, city=서울, sector=가정용, unitPrice=1000)");

        String result = myCommands.price("서울", "가정용");

        assertEquals("Price(id=1, city=서울, sector=가정용, unitPrice=1000)", result);
        verify(priceService, times(1)).getUnitPriceByCityAndSector("서울", "가정용");
    }

    @Test
    @DisplayName("bill total A B C")
    void testBillTotal() {
        when(priceService.billTotalOutput("서울", "가정용", 10)).thenReturn("6900");

        String result = myCommands.billTotal("서울", "가정용", 10);

        assertEquals("6900", result);
        verify(priceService, times(1)).billTotalOutput("서울", "가정용", 10);
    }
}
