package com.example.unikomwebresfulapi.controller;

import com.example.unikomwebresfulapi.dto.response.UploadFileResponse;
import com.example.unikomwebresfulapi.exception.ApplicationException;
import com.example.unikomwebresfulapi.service.file.FileStorageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("media")
@CrossOrigin("*")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        String uriString = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString()
                + "/media/view?fileName=" + fileName;
        return new UploadFileResponse(fileName, uriString, file.getContentType(), file.getSize());
    }

    @GetMapping("view")
    public @ResponseBody void showImage(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            logger.info("Không thể xác định loại tệp.");
        }
        if (contentType == null) {
            throw new ApplicationException("err.not-found");
        }
        File imageFile = new File(resource.getURL().getFile());
        response.setContentType("image/jpeg");
        InputStream in=new FileInputStream(imageFile);
        IOUtils.copy(in, response.getOutputStream());
    }

//    @GetMapping("/view")
//    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName, HttpServletRequest request) {
//        Resource resource = fileStorageService.loadFileAsResource(fileName);
//
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException e) {
//            logger.info("Không thể xác định loại tệp.");
//        }
//        if (contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"" )
//                .body(resource);
//    }
}
