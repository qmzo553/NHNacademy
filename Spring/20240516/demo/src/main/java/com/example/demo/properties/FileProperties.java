package com.example.demo.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("file")
@Getter
@Setter
public class FileProperties {

    private String accountFilePath;
    private String priceFilePath;
}
