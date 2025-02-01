package com.api.domain.Upload.Controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.api.domain.Upload.Service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UploadController {

    private final AmazonS3Client amazonS3Client;
    private final UploadService uploadService;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @ResponseBody
    @PostMapping(value="/upload", produces="application/json; charset=UTF-8")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = uploadService.uploadImg(file);
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ResponseBody
    @DeleteMapping(value="/upload", produces="application/json; charset=UTF-8")
    public ResponseEntity<String> deleteFile(@RequestParam String fileName){
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
        return ResponseEntity.ok(fileName);
    }
}
