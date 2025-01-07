package com.api.Product.Dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
public class ImageHandler {

    // 이미지 서버에 저장
    public String imgSave(MultipartFile image){

        return "저장 경로";
    }

    public String createStoreFileName(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(pos + 1);
        String uuid = UUID.randomUUID().toString();

        return uuid + "." + ext;
    }

}
