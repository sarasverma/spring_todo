package com.saras.todo.app.controller;

import com.saras.todo.app.model.Doodle;
import com.saras.todo.app.service.DoodleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/doodle")
public class DoodleController {

    @Autowired
    DoodleService service;

    @PostMapping("")
    public ResponseEntity<?> changeDoodle(@RequestPart MultipartFile imageFile){
        try{
            service.changeDoodle(imageFile);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getDoodle(){
        Doodle doodle = service.getDoodle();
        if(doodle == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // builder pattern bcz of headers
        return ResponseEntity.ok().contentType(MediaType.valueOf(doodle.getImageType())).body(doodle.getImageData());
//        other way to do that
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.valueOf(doodle.getImageType()));
//        return new ResponseEntity<>(doodle.getImageData(), headers, HttpStatus.OK);
    }
}
