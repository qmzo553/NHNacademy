package com.nhnacademy.customerservice.repository.file;

import com.nhnacademy.customerservice.domain.file.File;
import com.nhnacademy.customerservice.transaction.DbConnectionThreadLocal;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileRepositoryTest {

    private FileRepository fileRepository;
    File testFile;

    @BeforeEach
    void setUp() {
        DbConnectionThreadLocal.initialize();
        fileRepository = new FileRepositoryImpl();
        testFile = File.create("test", 1);
        fileRepository.save(testFile);
    }

    @AfterEach
    void tearDown() {
        DbConnectionThreadLocal.setSqlError(true);
        DbConnectionThreadLocal.reset();
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
