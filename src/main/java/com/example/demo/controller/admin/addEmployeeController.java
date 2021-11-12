package com.example.demo.controller.admin;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.employee;
import com.example.demo.service.FileUploadUtil;
import com.example.demo.service.authService;
import com.example.demo.service.admin.employeeAddingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
@Controller
public class addEmployeeController {
    @Autowired
    private authService auth;
    @Autowired
    private employeeAddingService eas; 
    @Autowired
    private FileUploadUtil fuu;
    @PostMapping("/addEmployee")
    public ResponseEntity<?> addAdmin(HttpServletRequest req,@RequestParam("image")MultipartFile uploadfile){
        Hashtable<String,String> data = new Hashtable<String,String>();
        String username  = req.getParameter("uname").trim();
        String pass = req.getParameter("password").trim();
        String email  = req.getParameter("email").trim();
        String dob = req.getParameter("dob").trim();
        String phone = req.getParameter("phone").trim();
        String name = req.getParameter("name").trim();
        String address = req.getParameter("address").trim();
        String err="";
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
            employee e = new employee();
            int employeeId =  auth.newId(2);
            e.setPhoneNo(phone);
            e.setDob(dob);
            e.setName(name);
            e.setPassword(pass);
            e.setEmail(email);
            e.setEmpId(employeeId);
            e.setAge(auth.calcuateAge(dob));
            e.setAddress(address);
            e.setUsername(username);
            
            try{
                fuu.saveFile(employeeId, "employee", uploadfile);
                e.setImage(StringUtils.cleanPath(uploadfile.getOriginalFilename()).replaceAll(" ", ""));
            }catch(Exception ex){
                e.setImage("#");
            }
            eas.addEmployee(e);
            data.put("result" , "success");
            return ResponseEntity.ok(data);
        }
        data.put("result" , "error");
        data.put("error", err);
        return ResponseEntity.ok(data);
    }
}