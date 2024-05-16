package com.example.demo.file;

import com.example.demo.domain.Account;
import com.example.demo.domain.Price;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class CsvDataParser implements DataParser {

    private final CsvMapper csvMapper;

    public CsvDataParser() {
        this.csvMapper = new CsvMapper();
    }

    @Override
    public List<Account> readAccount(String filePath) {
        return readCsvFile(filePath, Account.class);
    }

    @Override
    public List<Price> readPrice(String filePath) {
        return readCsvFile(filePath, Price.class);
    }

    private <T> List<T> readCsvFile(String filePath, Class<T> clazz) {
        CsvSchema schema = csvMapper.schemaFor(clazz).withHeader().withColumnReordering(true);

        try {
            File csvFile = new File(filePath);
            MappingIterator<T> it = csvMapper.readerFor(clazz).with(schema).readValues(csvFile);
            return it.readAll();
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
