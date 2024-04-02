package com.example.amazonbuckets3.services;

import com.amazonaws.services.s3.model.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class PublisherService {

    @Value("${aws.bucket.name}")
    private String bucketName;
    private final AmazonS3 s3Client;

    public PublisherService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }


    public void publishFile(MultipartFile file, String fileName) {
        File convertedFile = convertMultiPartFileToFile(file);
        try {
            uploadToS3(convertedFile, fileName);
        } finally {
            deleteConvertedFile(convertedFile);
        }
    }

    public Resource downloadFile(String objectKeyName) {
        S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucketName, objectKeyName));
        return new InputStreamResource(s3Object.getObjectContent());
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedFile;
    }

    private void uploadToS3(File file, String fileName) {
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
    }

    public void deleteObject(String objectKey) {
        s3Client.deleteObject(new DeleteObjectRequest(bucketName, objectKey));
    }

    private void deleteConvertedFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

}
