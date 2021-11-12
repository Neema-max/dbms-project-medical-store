package com.example.demo.service.customer;

import javax.servlet.http.HttpSession;

import com.example.demo.model.customer;
import com.example.demo.repository.authRepo;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Service
public class customerLoginServ {
    // login wisely 
    @Autowired
    private authRepo auth;
    public void login(HttpSession session, String username , String customerId){
        session.setAttribute("userId", customerId);
    }
    public int getCustomerId(String username){
        customer c1 = auth.findCustomer(username); 
        return c1.getCustomerId();
    }
}