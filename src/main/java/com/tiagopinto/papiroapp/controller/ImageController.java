package com.tiagopinto.papiroapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ImageController {

    @GetMapping("/images/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) throws IOException {

        String path = Paths.get("src/main/resources/images").toAbsolutePath().toString();

        BufferedImage bImage = ImageIO.read(new File(path+File.separator+filename));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] data = bos.toByteArray();

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(data);
    }
}
