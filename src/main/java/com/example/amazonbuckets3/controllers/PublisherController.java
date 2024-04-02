package com.example.amazonbuckets3.controllers;


import com.example.amazonbuckets3.services.PublisherService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public void publisherFileToBucket(MultipartFile multipartFile, String fileName) {
        publisherService.publishFile(multipartFile, fileName);
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        publisherService.publishFile(file, fileName);
    }

    @GetMapping("/download/{objectKeyName}")
    @ResponseStatus(HttpStatus.OK)
    public Resource downloadFile(@PathVariable String objectKeyName) {
        return publisherService.downloadFile(objectKeyName);
    }

    @DeleteMapping("/delete/{objectKey}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFile(@PathVariable String objectKey) {
        publisherService.deleteObject(objectKey);
    }
}
