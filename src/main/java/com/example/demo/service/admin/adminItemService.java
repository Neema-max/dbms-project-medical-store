package com.example.demo.service.admin;

import com.example.demo.model.items;
import com.example.demo.repository.admin.adminItemRepository;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Service
public class adminItemService {
    @Autowired
    private adminItemRepository air;
    
    public void addItem(items i){
        air.addItem(i);
    }
    public void updateItem(items i){
        air.updateItem(i);
    }
}