package com.example.demo.command;

import com.example.demo.service.AuthenticationService;
import com.example.demo.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class MyCommands {

    private final AuthenticationService authenticationService;
    private final PriceService priceService;

    @ShellMethod
    public String login(String id, String password) {

        return authenticationService.login(id, password).toString();
    }

    @ShellMethod
    public String logout() {
        return "good bye";
    }

    @ShellMethod
    public String currentUser() {
        return authenticationService.getCurrentLoginList();
    }

    @ShellMethod
    public String city() {
        return priceService.getCities();
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
