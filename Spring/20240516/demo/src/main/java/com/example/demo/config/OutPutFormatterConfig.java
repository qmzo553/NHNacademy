package com.example.demo.config;

import com.example.demo.formmat.EnglishOutputFormatter;
import com.example.demo.formmat.KoreanOutputFormatter;
import com.example.demo.formmat.OutputFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OutPutFormatterConfig {


    @Bean
    @ConditionalOnProperty(value = "language", havingValue = "eng")
    EnglishOutputFormatter englishOutputFormatter() {
        return new EnglishOutputFormatter();
    }

    @Bean
    @ConditionalOnProperty(value = "language", havingValue = "kor")
    KoreanOutputFormatter koreanOutputFormatter() {
        return new KoreanOutputFormatter();
    }

    @Bean
    @ConditionalOnMissingBean(OutputFormatter.class)
    OutputFormatter defaultOutputFormatter() {
        return new EnglishOutputFormatter();
    }
}
