package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.service.FirebaseImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/")
public class FirebaseImageController {
    @Autowired
    FirebaseImageService imageService;

    @PostMapping("/imageFirebase")
    public String create(@RequestParam(name = "file") MultipartFile file) {
        String imageUrl="";
        try {

            imageUrl = imageService.save(file);

//                String imageUrl = imageService.getImageUrl(fileName);
//                System.out.println(fileName);
            //System.out.println(imageUrl);

            // do whatever you want with that

        } catch (Exception e) {
            //  throw internal error;
            System.out.println(e.toString());
        }


        return imageUrl;
    }
}
