package com.sa.socialcoding.sms.controller;

import com.sa.socialcoding.sms.model.File;
import com.sa.socialcoding.sms.repository.FileRepository;
import com.sa.socialcoding.sms.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.Deflater;

@RestController
@RequestMapping(path="/file")
public class FileController {
    @Autowired
    private FileRepository fileRepository;

    @PostMapping(path = "/upload")
    public String uploadFile(
            @RequestHeader(HttpHeaders.ACCEPT) String language,
            @RequestParam("fileName") String fileName,
            @RequestParam("fileType") String fileType,
            @RequestParam("data") MultipartFile data) throws IOException {
        File file = new File();
        file.setFileName(fileName);
        file.setFileType(fileType);
     //   file.setData(ImageUtil.compressImage(data.getBytes()));
        file.setData(data.getBytes());
        fileRepository.save(file);
        return "Saved successfully";
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
    //    Optional<File> file = fileRepository.findFileByFileName(fileName);
        Optional<File> file = fileRepository.findById(3);
    //    byte[] image = ImageUtil.decompressImage(file.get().getData());
        byte[] image = file.get().getData();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

}
