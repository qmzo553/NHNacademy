package com.nhnacademy.customerservice.service.file;

import com.nhnacademy.customerservice.domain.file.File;
import com.nhnacademy.customerservice.repository.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public List<File> getFilesByInquiryId(long inquiryId) {
        if(inquiryId <= 0) {
            throw new IllegalArgumentException();
        }

        return fileRepository.getFilesByInquiryId(inquiryId);
    }

    @Override
    public void saveFiles(long inquiryId, List<MultipartFile> files) {
        if(files == null || inquiryId <= 0) {
            throw new IllegalArgumentException();
        }

        if(!files.isEmpty()) {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                fileRepository.save(File.create(fileName, inquiryId));
            }
        }
    }
}
