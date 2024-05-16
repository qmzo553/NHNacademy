package com.example.demo.file;

import com.example.demo.domain.Account;
import com.example.demo.domain.Price;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class JsonDataParser implements DataParser {

    private final ObjectMapper objectMapper;

    public JsonDataParser() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<Account> readAccount(String filePath) {
        return readJsonFile(filePath, new TypeReference<List<Account>>() {});
    }

    @Override
    public List<Price> readPrice(String filePath) {
        return readJsonFile(filePath, new TypeReference<List<Price>>() {});
    }

    private <T> List<T> readJsonFile(String filePath, TypeReference<List<T>> typeReference) {
        try {
            return objectMapper.readValue(new File(filePath), typeReference);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
