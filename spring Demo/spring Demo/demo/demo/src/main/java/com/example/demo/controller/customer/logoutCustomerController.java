package com.example.demo.controller.customer;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import com.example.demo.service.customer.customerLogoutServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class logoutCustomerController {
    @Autowired
    private customerLogoutServ clos;
    @PostMapping("/logout-customer")
    public ResponseEntity<?> customerLogout(HttpSession session){
        Hashtable<String,String> data = new Hashtable<String,String>();
        clos.logout(session);
        return ResponseEntity.ok(data);
    }
}