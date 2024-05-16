package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.file.DataParser;
import com.example.demo.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
@Component
public class AuthenticationService {

    private final DataParser dataParser;
    private final FileProperties fileProperties;

    public List<Account> getAccounts() {
        return dataParser.readAccount(fileProperties.getAccountFilePath());
    }

}
