package com.nhnacademy.customerservice.service.file;

import com.nhnacademy.customerservice.domain.file.File;
import com.nhnacademy.customerservice.repository.file.FileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    private FileRepository fileRepository = mock(FileRepository.class);
    private FileService fileService;
    private File testFile;

    @BeforeEach
    void setUp() {
        fileService = new FileServiceImpl(fileRepository);
        testFile = File.create("test", 1);
    }

    @Test
    @DisplayName("get files by inquiryId")
    void getFilesByInquiryId() {
        when(fileRepository.getFilesByInquiryId(anyLong())).thenReturn(List.of(testFile));
        fileService.getFilesByInquiryId(1L);
        verify(fileRepository, times(1)).getFilesByInquiryId(anyLong());
    }

    @Test
    @DisplayName("save file")
    void saveFile() {
        MultipartFile testMultipartFile = mock(MultipartFile.class);
        List<MultipartFile> multipartFiles = List.of(testMultipartFile);
        when(testMultipartFile.getOriginalFilename()).thenReturn("filename");
        when(fileRepository.save(any(File.class))).thenReturn(1);

        fileService.saveFiles(1, multipartFiles);
        verify(fileRepository, times(1)).save(any(File.class));

        assertThrows(IllegalArgumentException.class, () -> fileService.saveFiles(-1, null));
    }
}
