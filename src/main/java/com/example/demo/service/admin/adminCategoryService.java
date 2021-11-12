package com.example.demo.service.admin;


import com.example.demo.model.category;
import com.example.demo.repository.admin.adminCategoryRepository;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Service
public class adminCategoryService {
    @Autowired
    private adminCategoryRepository acr;
    public void addCateogry(category c){
        acr.addCategory(c);
    }
    public void updateCat(int catId, String cat){
        acr.updateCat(catId , cat);
    }
}