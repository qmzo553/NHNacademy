package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.file.DataParser;
import com.example.demo.properties.FileProperties;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Getter
public class AuthenticationService {

    private final DataParser dataParser;
    private final FileProperties fileProperties;
    private List<Account> accountList;
    private List<Account> currentLoginList;
    private Account currentLogin;

    @PostConstruct
    public void getAccounts() {
        accountList = dataParser.readAccount(fileProperties.getAccountFilePath());
        currentLoginList = new ArrayList<>();
        currentLogin = null;
    }

    public String getCurrentLoginList() {
        if(currentLoginList.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for(Account account : currentLoginList) {
            builder.append(account.toString()).append(System.lineSeparator());
        }

        return builder.toString();
    }

    public Account login(String id, String password) {
        Optional<Account> accountOptional = accountList.stream()
                .filter(account -> account.getId().equals(id))
                .filter(account -> account.getPassword().equals(password))
                .findFirst();

        if (accountOptional.isEmpty()) {
            throw new IllegalArgumentException("로그인 실패");
        }

        currentLogin = accountOptional.get();
        currentLoginList.add(accountOptional.get());

        return accountOptional.get();
    }

    public boolean isLogin() {
        return currentLogin != null;
    }

    public String logout() {
        currentLoginList.remove(currentLogin);
        String name = currentLogin.getName();
        currentLogin = null;

        return name + " good bye";
    }
}
