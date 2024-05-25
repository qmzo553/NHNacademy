package com.nhnacademy.customerservice.service.file;

import com.nhnacademy.customerservice.domain.file.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    List<File> getFilesByInquiryId(long inquiryId);
    void saveFiles(long inquiryId, List<MultipartFile> files);
}
