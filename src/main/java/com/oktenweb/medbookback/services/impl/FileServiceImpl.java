package com.oktenweb.medbookback.services.impl;

import com.oktenweb.medbookback.services.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Value("images")
    private String uploadPath;

    @Override
    public String storeFile(MultipartFile file) throws IOException {
        BufferedImage image = null;

        try {
            image = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (image != null) {
            File uploadFolder = new ClassPathResource("static" + File.separator + uploadPath).getFile();
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
            final File targetFile = new File(uploadFolder.getAbsolutePath() + File.separator + file.getOriginalFilename());
            targetFile.createNewFile();
            file.transferTo(targetFile);
            return file.getOriginalFilename();
        } else {
            return null;
        }

//                String path = System.getProperty("user.dir")+File.separator
//                + "target" + File.separator
//                + "classes" + File.separator
//                + "static" + File.separator
//                + "images" + File.separator;
//        file.transferTo(new File(path + file.getOriginalFilename()));
//        return file.getOriginalFilename();
    }


}
