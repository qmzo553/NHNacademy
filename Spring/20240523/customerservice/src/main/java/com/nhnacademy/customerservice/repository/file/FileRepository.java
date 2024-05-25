package com.nhnacademy.customerservice.repository.file;

import com.nhnacademy.customerservice.domain.file.File;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository {

    List<File> getFilesByInquiryId(long inquiryId);
    int save(File file);

}
