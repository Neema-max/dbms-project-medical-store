package com.example.demo.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
@Component
@Service
public class FileUploadUtil {
    private  BufferedImage cropImageSquare(byte[] image) throws IOException {
        InputStream in = new ByteArrayInputStream(image);
        BufferedImage originalImage = ImageIO.read(in);
        int height = originalImage.getHeight();
        int width = originalImage.getWidth();
        
        if (height == width) {
          return originalImage;
        }
        int squareSize = (height > width ? width : height);

        int xc = width / 2;
        int yc = height / 2;

        BufferedImage croppedImage = originalImage.getSubimage(
            xc - (squareSize / 2), 
            yc - (squareSize / 2), 
            squareSize,            
            squareSize             
        );
        return croppedImage;
      }

    public  void saveFile(int id, String x,MultipartFile uploadfile,String j) {
        try {
            String filename = StringUtils.cleanPath(j);
            String directory = "user-photos/"+x+"/"+String.valueOf(id);
            Path filepath = Paths.get(directory);
            if (!Files.exists(filepath)) {
                Files.createDirectories(filepath);
            }
            BufferedImage croppedImage = cropImageSquare(uploadfile.getBytes());
            File outputfile = new File(directory+ "/"+filename);
            ImageIO.write(croppedImage,"png", outputfile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    public  void saveFile(int id, String x,MultipartFile uploadfile) {
        try {
            String filename = StringUtils.cleanPath(uploadfile.getOriginalFilename()).replaceAll(" ", "");
            String directory = "user-photos/"+x+"/"+String.valueOf(id);
            Path filepath = Paths.get(directory);
            if (!Files.exists(filepath)) {
                Files.createDirectories(filepath);
            }
            BufferedImage croppedImage = cropImageSquare(uploadfile.getBytes());
            File outputfile = new File(directory+ "/"+filename);
            ImageIO.write(croppedImage,"png", outputfile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
}