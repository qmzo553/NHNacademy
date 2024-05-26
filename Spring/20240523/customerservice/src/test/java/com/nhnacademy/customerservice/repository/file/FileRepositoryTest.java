package com.nhnacademy.customerservice.repository.file;

import com.nhnacademy.customerservice.config.DataSourceConfig;
import com.nhnacademy.customerservice.domain.file.File;
import com.nhnacademy.customerservice.transaction.DbConnectionThreadLocal;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DataSourceConfig.class, FileRepositoryImpl.class, DbConnectionThreadLocal.class})
class FileRepositoryTest {

    @Autowired
    private DbConnectionThreadLocal dbConnectionThreadLocal;

    @Autowired
    private FileRepository fileRepository;
    File testFile;

    @BeforeEach
    void setUp() {
        dbConnectionThreadLocal.initialize();
        testFile = File.create("test", 1);
        fileRepository.save(testFile);
    }

    @AfterEach
    void tearDown() {
        dbConnectionThreadLocal.setSqlError(true);
        dbConnectionThreadLocal.reset();
    }

    @Test
    @DisplayName("get files by inquiryId")
    void getFilesByInquiryId() {
        List<File> files = fileRepository.getFilesByInquiryId(testFile.getInquiryId());
        assertEquals(1, files.size());
    }

    @Test
    @DisplayName("save file")
    void save() {
        File newFile = File.create("test", 1L);
        int result = fileRepository.save(newFile);
        assertEquals(1, result);
    }
}
