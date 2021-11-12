package com.example.demo.controller.employee;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.model.employee;
import com.example.demo.service.authService;
import com.example.demo.service.employee.employeeLoginServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class employeeLoginController {
    @Autowired
    private employeeLoginServ emp;
    @Autowired
    private authService auth;
    @PostMapping("/login-employee")
    public ResponseEntity<?> employeeLogin(HttpServletRequest req , Model model , HttpSession session){   
        Hashtable<String,String> data = new Hashtable<String,String>();
        String username  = req.getParameter("uname").trim();
        String userId = String.valueOf(emp.getEmployeeId(username));
        emp.login(session,username,userId);
        return ResponseEntity.ok(data); 
    }
    @PostMapping("/logout-employee")
    public ResponseEntity<?> employeeLogout(HttpSession session){
        Hashtable<String,String> data = new Hashtable<String,String>();
        emp.logout(session);
        return ResponseEntity.ok(data);
    }
    @PostMapping("/employeechangePass")
    public ResponseEntity<?> changePass(HttpSession session , HttpServletRequest req){
        Hashtable<String,String> data = new Hashtable<String,String>();
        employee c = auth.getEmployee(session);
        String newPass = req.getParameter("newpass").toString().trim();
        String oldPass = req.getParameter("oldpass").toString().trim();
        int x = auth.checkCredAndPost(c.getUsername(), oldPass);
        if(x==2){
            emp.updatePass(newPass,c.getEmpId());
            data.put("result", "success");
            return ResponseEntity.ok(data);
        }
        data.put("result", "err");
        data.put("error","wrong Password");
        return ResponseEntity.ok(data);
    }
    @PostMapping("/employeeEditProfile")
    public ResponseEntity<?> editProfile(HttpSession session , HttpServletRequest req){
        Hashtable<String,String> data = new Hashtable<String,String>();

        int cid = Integer.parseInt(session.getAttribute("userId").toString());
        String name = req.getParameter("name");
        String username = req.getParameter("uname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String add = req.getParameter("address");
        String dob = req.getParameter("dob");
        String err="";
        employee present = auth.getEmployee(session); 
        if( (!present.getUsername().equals(username)) && (auth.checkUsernameExistance(username)!=0) ){
            err = "username already exist";
        }else if((auth.checkEmailExistance(email) != 0)&&(!present.getEmail().equals(email))){
            err = "email already exist";
        }else{
            employee c = new employee();
            c.setName(name);
            c.setEmpId(cid);
            c.setAddress(add);
            c.setEmail(email);
            c.setDob(dob);
            c.setPhoneNo(phone);
            c.setUsername(username);
            c.setAge(auth.calcuateAge(dob));
            emp.editProfile(c);
            data.put("result","success");
            return ResponseEntity.ok(data);
        }
        data.put("result","error");
        data.put("error",err);
        return ResponseEntity.ok(data);
    }
}
