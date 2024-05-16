package com.example.demo.command;

import com.example.demo.domain.Account;
import com.example.demo.domain.Price;
import com.example.demo.file.CsvDataParser;
import com.example.demo.file.DataParser;
import com.example.demo.file.JsonDataParser;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class MyCommands {

    DataParser dataParser = new JsonDataParser();
    DataParser dataParser2 = new CsvDataParser();

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
        List<Account> accounts = dataParser.readAccount("src/main/resources/file/account.json");
        List<Price> prices = dataParser.readPrice("src/main/resources/file/Tariff.json");
        List<Account> accounts2 = dataParser2.readAccount("src/main/resources/file/account.csv");
        List<Price> prices2 = dataParser2.readPrice("src/main/resources/file/Tariff.csv");

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
    public String billTotal(String city, String sector, String price) {
        return null;
    }

}
