package com.springboot.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.model.UploadFileResponse;
import com.springboot.model.User;
import com.springboot.service.FileStorageService;

//https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/

@RestController
//@CrossOrigin
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    
    @PostMapping("/uploadFile")
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
    	System.out.println("uploadFile>>>>>>>>>>> calling..!");
    	String fileName = fileStorageService.storeFile(file);

    	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
    			.path("/downloadFile/")
    			.path(fileName)
    			.toUriString();


    	return new ResponseEntity<UploadFileResponse>(new UploadFileResponse(fileName, fileDownloadUri,
    			file.getContentType(), file.getSize()), HttpStatus.OK);
    }
    
    public UploadFileResponse uploadFile1(@RequestParam("file") MultipartFile file) {
    	String fileName = fileStorageService.storeFile(file);

    	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
    			.path("/downloadFile/")
    			.path(fileName)
    			.toUriString();


    	return new UploadFileResponse(fileName, fileDownloadUri,
    			file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public ResponseEntity<List<UploadFileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        
    	System.out.println("called uploadMultipleFiles >>> ");
    	List<UploadFileResponse> filesUploaded = Arrays.asList(files)
                .stream()
                .map(file -> uploadFile1(file))
                .collect(Collectors.toList());
    	
    	
    	return new ResponseEntity<List<UploadFileResponse>>(filesUploaded, HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}