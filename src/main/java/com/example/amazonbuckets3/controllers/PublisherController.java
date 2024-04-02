package com.example.amazonbuckets3.controllers;


import com.example.amazonbuckets3.services.PublisherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        publisherService.publisher(multipartFile, fileName);
    }
}
