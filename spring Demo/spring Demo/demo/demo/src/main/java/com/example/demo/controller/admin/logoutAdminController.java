package com.example.demo.controller.admin;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import com.example.demo.service.admin.adminLogoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class logoutAdminController {
    @Autowired
    private adminLogoutService alos;
    @PostMapping("/logout-admin")
    public ResponseEntity<?> customerLogout(HttpSession session){
        Hashtable<String,String> data = new Hashtable<String,String>();
        alos.logout(session);
        return ResponseEntity.ok(data);
    }
}