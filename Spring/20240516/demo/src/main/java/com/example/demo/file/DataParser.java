package com.example.demo.file;

import com.example.demo.domain.Account;
import com.example.demo.domain.Price;

import java.util.List;

public interface DataParser {

    List<Account> readAccount(String filePath);
    List<Price> readPrice(String filePath);
}
