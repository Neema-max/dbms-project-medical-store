package com.example.demo.controller.admin;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.admin;
import com.example.demo.service.FileUploadUtil;
import com.example.demo.service.authService;
import com.example.demo.service.admin.adminAddingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class addAdminController {
    @Autowired
    private authService auth;
    @Autowired
    private adminAddingService aas; 
    @Autowired
    private FileUploadUtil fuu;
    @PostMapping("/addAdmin")
    public ResponseEntity<?> addAdmin(HttpServletRequest req , @RequestParam("image")MultipartFile uploadfile){
        Hashtable<String,String> data = new Hashtable<String,String>();
        String username  = req.getParameter("uname").trim();
        String pass = req.getParameter("password").trim();
        String email  = req.getParameter("email").trim();
        String dob = req.getParameter("dob").trim();
        String phone = req.getParameter("phone").trim();
        String name = req.getParameter("name").trim();
        String address = req.getParameter("address").trim();

        String err="kuch to hai";
        if(username.contains(" ")){
            err = "space in between username is not allowed";
        }else if(email.contains(" ")){
            err = "space in between email is not allowed";
        }else if(pass.contains(" ")){
            err = "space in between password is not allowed";
        }else if(auth.checkUsernameExistance(username) != 0 ){
            err = "username already exist";
        }else if(auth.checkEmailExistance(email) != 0){
            err = "email already exist";
        }else{
            admin c1 = new admin();
            int adminId =  auth.newId(3);
            c1.setPhoneNo(phone);
            c1.setDob(dob);
            c1.setName(name);
            c1.setPassword(pass);
            c1.setEmail(email);
            c1.setAdminId(adminId);
            c1.setAge(auth.calcuateAge(dob));
            c1.setAddress(address);
            c1.setUsername(username);
            try{
                fuu.saveFile(adminId, "admin", uploadfile);
                c1.setImage(StringUtils.cleanPath(uploadfile.getOriginalFilename()).replaceAll(" ", ""));
            }catch(Exception e){
                c1.setImage("#");
            }
            aas.addAdmin(c1);
            data.put("result" , "success");
            return ResponseEntity.ok(data);
        }
        data.put("result" , "error");
        data.put("error", err);
        return ResponseEntity.ok(data);
    }
}