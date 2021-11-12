package com.example.demo.service.admin;

import javax.servlet.http.HttpSession;

import com.example.demo.model.admin;
import com.example.demo.repository.authRepo;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Service
public class adminLoginService {
    @Autowired
    private authRepo auth;
    public void login(HttpSession session, String username , String adminId){
        session.setAttribute("userId", adminId);
    }
    public int getAdminId(String username){
        admin a1 = auth.findAdmin(username); 
        return a1.getAdminId();
    }
}