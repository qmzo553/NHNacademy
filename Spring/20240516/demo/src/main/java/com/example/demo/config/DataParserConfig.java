package com.example.demo.config;

import com.example.demo.file.CsvDataParser;
import com.example.demo.file.DataParser;
import com.example.demo.file.JsonDataParser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataParserConfig {

    @Bean
    @ConditionalOnProperty(value = "parser", havingValue = "json")
    JsonDataParser jsonDataParser() {
        return new JsonDataParser();
    }

    @Bean
    @ConditionalOnProperty(value = "parser", havingValue = "csv")
    CsvDataParser csvDataParser() {
        return new CsvDataParser();
    }

    @Bean
    @ConditionalOnMissingBean(DataParser.class)
    DataParser defaultDataParser() {
        return new JsonDataParser();
    }
}
