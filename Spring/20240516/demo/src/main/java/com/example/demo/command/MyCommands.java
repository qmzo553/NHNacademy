package com.example.demo.command;

import com.example.demo.domain.Account;
import com.example.demo.domain.Price;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@RequiredArgsConstructor
@ShellComponent
public class MyCommands {

    private final AuthenticationService authenticationService;
    private final PriceService priceService;

    @ShellMethod
    public String login(String name, String password) {
        return null;
    }

    @ShellMethod
    public String logout() {
        return "good bye";
    }

    @ShellMethod
    public String currentUser() {

        return "hi";
    }

    @ShellMethod
    public String city() {
        return null;
    }

    @ShellMethod
    public String sector(String city) {
        return null;
    }

    @ShellMethod
    public String price(String city, String sector) {
        return null;
    }

    @ShellMethod
    public String billTotal(String city, String sector, int usage) {
        return priceService.billTotalOutput(city, sector, usage);
    }

}
