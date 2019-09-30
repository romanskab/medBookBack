package com.oktenweb.medbookback.controllers;

import com.oktenweb.medbookback.entity.CustomResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

@RestController
@CrossOrigin(origins = "*")
public class PhotoController {

//    @PostMapping("/save/user/photo")
//    public CustomResponse photo(MultipartFile image) throws IOException {
//        System.out.println(image.getOriginalFilename());
//        String path = System.getProperty("user.dir") + File.separator
//                + "src" + File.separator
//                + "main" + File.separator
//                + "resources" + File.separator
//                + "static" + File.separator
//                + "images" + File.separator;
//        image.transferTo(new File(path + image.getOriginalFilename()));
//        return new CustomResponse("ok!", true);
//    }
}
