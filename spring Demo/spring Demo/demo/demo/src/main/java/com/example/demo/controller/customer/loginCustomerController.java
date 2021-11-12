package com.example.demo.controller.customer;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.service.customer.customerLoginServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class loginCustomerController {
    @Autowired
    private customerLoginServ cls; 
    @PostMapping("/login-customer")
    public ResponseEntity<?> customerLogin(HttpServletRequest req , Model model , HttpSession session){   
        Hashtable<String,String> data = new Hashtable<String,String>();
        String username  = req.getParameter("uname").trim();
        String userId = String.valueOf(cls.getCustomerId(username));
        cls.login(session,username,userId);
        return ResponseEntity.ok(data); 
    }
}