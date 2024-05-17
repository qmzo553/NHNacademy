package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.domain.Price;
import com.example.demo.file.DataParser;
import com.example.demo.properties.FileProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationService {

    private final DataParser dataParser;
    private final FileProperties fileProperties;
    private List<Account> accountList;
    private List<Account> currentLoginList;

    @PostConstruct
    public void getAccounts() {
        accountList = dataParser.readAccount(fileProperties.getAccountFilePath());
        currentLoginList = new ArrayList<>();
    }

    public List<Account> getCurrentLoginList() {
        return currentLoginList;
    }

    public Account login(String id, String password) {
        Optional<Account> accountOptional = accountList.stream()
                .filter(account -> account.getId().equals(id))
                .filter(account -> account.getPassword().equals(password))
                .findFirst();

        if (accountOptional.isEmpty()) {
            throw new IllegalArgumentException("로그인 실패");
        }

        log.info("login([{}, {}])", id, password);
        currentLoginList.add(accountOptional.get());

        return accountOptional.get();
    }

    public void logout() {

    }

}
