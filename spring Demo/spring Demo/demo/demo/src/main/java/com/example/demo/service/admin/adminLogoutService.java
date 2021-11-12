package com.example.demo.service.admin;

import javax.servlet.http.HttpSession;

import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
@Component
@Service
public class adminLogoutService {
    public void logout(HttpSession session){
        session.removeAttribute("userId");
    }
}