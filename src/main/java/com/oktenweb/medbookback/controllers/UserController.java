package com.oktenweb.medbookback.controllers;

import com.oktenweb.medbookback.entity.CustomResponse;
import com.oktenweb.medbookback.entity.User;
import com.oktenweb.medbookback.services.FileService;
import com.oktenweb.medbookback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @GetMapping("/user/currentRole")
    public String currentAuthorities() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities.toArray()[0].toString();
    }

    @GetMapping("/user/current")
    public User currentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);
        user.setPassword(null);
        return user;
    }

    @PostMapping("/save/user/photo")
    public CustomResponse saveFile(@RequestParam("image") MultipartFile file) throws IOException {
        fileService.storeFile(file);
        return new CustomResponse("save/user/photo ok!", true);
    }


//    @PostMapping("/save/user/photo")
//    public CustomResponse photo(MultipartFile image) throws IOException {
//        System.out.println(image.getOriginalFilename());
//        String path = System.getProperty("user.dir")+File.separator
//                + "src" + File.separator
//                + "main" + File.separator
//                + "resources" + File.separator
//                + "static" + File.separator
//                + "images" + File.separator;
//        image.transferTo(new File(path + image.getOriginalFilename()));
//        return new CustomResponse("save/user/photo ok!", true);
//    }
//
//    @PostMapping("/save/user/photo/target")
//    public CustomResponse photoTarget(MultipartFile image) throws IOException {
//        System.out.println(image.getOriginalFilename());
//        String path = System.getProperty("user.dir")+File.separator
//                + "target" + File.separator
//                + "classes" + File.separator
//                + "static" + File.separator
//                + "images" + File.separator;
//        image.transferTo(new File(path + image.getOriginalFilename()));
//        return new CustomResponse("save/user/photo/target ok!", true);
//    }
}
