package com.example.unikomwebresfulapi.service.file;

import com.example.unikomwebresfulapi.exception.FileStorageException;
import com.example.unikomwebresfulapi.exception.MyFileNotFoundException;
import com.example.unikomwebresfulapi.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Không thể tạo thư mục lưu trữ các tệp đã tải lên.", e);
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Xin lỗi! Tên tệp chứa chuỗi đường dẫn không hợp lệ" + fileName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new FileStorageException("Không thể lưu trữ tệp" + fileName + ". Xin vui lòng thử lại!", e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("Tệp không tồn tại " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new MyFileNotFoundException("Tệp không tồn tại " + fileName, e);
        }
    }
}
