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

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
@Component
@Service
public class FileUploadUtil {
    /*@PostConstruct
    private void initializeAmazon() {
       AWSCredentials credentials = new BasicAWSCredentials("AKIAXS6LMX27DFGJZVG7", "SBbWrIHrJzlVxjzuFn+ib1ofKUg1DB6jWlgzdhdW");
       s3client = new AmazonS3Client(credentials);
    }*/
    private void uploadFileTos3bucket(String fileName, File file,String dir) {
        System.out.println("111111111111");
        System.out.println("------------");
        System.out.println("------------");
        System.out.println("------------");
        AWSCredentials credentials = new BasicAWSCredentials("AKIAXS6LMX27DFGJZVG7", "SBbWrIHrJzlVxjzuFn+ib1ofKUg1DB6jWlgzdhdW");
        AmazonS3 s3client = new AmazonS3Client(credentials);
        System.out.println(s3client.getBucketAcl("mybucket-user-photos"));
        System.out.println("------------");
        System.out.println("------------");
        System.out.println("------------");
        System.out.println("------------");
        s3client.putObject(new PutObjectRequest("mybucket-user-photos/"+ dir, fileName, file));
    }
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
            uploadFileTos3bucket(filename,outputfile,directory);
            
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
            uploadFileTos3bucket(filename,outputfile,directory);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
}
