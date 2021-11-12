package com.example.demo.service.admin;

import com.example.demo.model.admin;
import com.example.demo.repository.admin.adminEditProfileRepo;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Service
public class adminEditProfile {
    @Autowired
    private adminEditProfileRepo aepr;
    public void editProfile(admin a){
        aepr.editProfile(a);
    }
    public void updatePass(String newpass , int adminId){
        aepr.updatePass(newpass,adminId);
    }
}