package com.example.amazonbuckets3.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class PublisherService {

    @Value("${bucket-name}")
    private String bucketName;
    private final AmazonS3 s3Client;

    public PublisherService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }


    public void publisher(MultipartFile multipartFile,String fileName) {
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, multipartFile.));
    }


    private void uploadFile(MultipartFile file, String fileName) {
        File fileObj = convertMultiPartFileToFile(file);
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException ignored) {
        }
        return convertedFile;
    }

}
