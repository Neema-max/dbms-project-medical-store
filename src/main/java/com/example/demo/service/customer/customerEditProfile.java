package com.example.demo.service.customer;

import com.example.demo.model.customer;
import com.example.demo.repository.customer.customerEditProfileRepo;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Service
public class customerEditProfile {
    @Autowired
    private customerEditProfileRepo cepr; 
    public void editProfile(customer c){
        cepr.editProfile(c);
    }
    public void updatePass(String newPass,int cid){
        cepr.updatePass(newPass,cid);
    }
}