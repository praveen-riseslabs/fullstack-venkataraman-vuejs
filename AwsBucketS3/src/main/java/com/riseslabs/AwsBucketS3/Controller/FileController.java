package com.riseslabs.AwsBucketS3.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.riseslabs.AwsBucketS3.Service.AmazonS3Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/s3")
public class FileController {

    @Autowired
    private AmazonS3Service amazonS3Service;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam String bucketName,
    		@RequestParam String key,
    		@RequestParam("file") MultipartFile file,
    		@RequestParam String filePath) throws IOException {
    	File convertedFile = convertMultiPartFileToFile(file, filePath);
        amazonS3Service.uploadFile(bucketName, key, convertedFile);
        convertedFile.delete(); // Delete the temporary file
        return "File uploaded successfully to S3!";
    }

    @GetMapping("/retrieve")
    public String retrieveFile(@RequestParam String bucketName,
                              @RequestParam String key,
                              @RequestParam String destinationDirectory,
                              @RequestParam String newFileName) {
        byte[] fileBytes = amazonS3Service.getFile(bucketName, key);

        if (fileBytes != null) {
            String filePath = destinationDirectory + File.separator + newFileName;
            try {
                Files.write(Paths.get(filePath), fileBytes);
                return "File downloaded and saved successfully!";
            } catch (IOException e) {
                return "Failed to save the file: " + e.getMessage();
            }
        } else {
            return "File not found in the specified bucket.";
        }
    }



    private File convertMultiPartFileToFile(MultipartFile file, String filePath) throws IOException {
        File convertedFile = new File(filePath);
        file.transferTo(convertedFile);
        return convertedFile;
    }
}

