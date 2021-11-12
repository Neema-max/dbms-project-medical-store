package com.example.demo.service.customer;

import javax.servlet.http.HttpSession;

import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
@Component
@Service
public class customerLogoutServ {
    public void logout(HttpSession session){
        session.removeAttribute("userId");
    }
}