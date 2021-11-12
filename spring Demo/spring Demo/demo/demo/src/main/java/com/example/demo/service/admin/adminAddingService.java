package com.example.demo.service.admin;


import com.example.demo.model.admin;
import com.example.demo.repository.admin.adminAddingRepo;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Service
public class adminAddingService {
    @Autowired
    private adminAddingRepo aar;
    
    public void addAdmin(admin a){
        aar.addAdmin(a);
    }
}