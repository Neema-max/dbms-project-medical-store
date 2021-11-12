package com.example.demo.service.admin;

import com.example.demo.model.itemImage;
import com.example.demo.repository.admin.itemImageRepo;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Service
public class itemImageService {
    @Autowired
    private itemImageRepo iir;
    public void saveItemImage(itemImage i){
        iir.save(i);
    }
}