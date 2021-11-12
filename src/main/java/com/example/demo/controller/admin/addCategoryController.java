package com.example.demo.controller.admin;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.category;
import com.example.demo.service.FileUploadUtil;
import com.example.demo.service.authService;
import com.example.demo.service.admin.adminCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class addCategoryController {
    @Autowired
    private adminCategoryService acs;
    @Autowired
    private authService auth;
    @Autowired
    private FileUploadUtil fuu;
    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory( HttpServletRequest req,@RequestParam("image")MultipartFile uploadfile){
        Hashtable<String,String> data = new Hashtable<String,String>();
        String cat = req.getParameter("category");
        int catId = auth.newId(5);
        category c1 = new category();
        try{
            fuu.saveFile(catId, "category", uploadfile);
            c1.setImage(StringUtils.cleanPath(uploadfile.getOriginalFilename()).replaceAll(" ", ""));
        }catch(Exception e){
            c1.setImage("#");
        }
        c1.setCatId(catId);
        c1.setCategory(cat);
        acs.addCateogry(c1);
        data.put("result", "success");
        return ResponseEntity.ok(data);
    }
    @PostMapping("/editCategory/{categoryId}")
    public String editCategory(@PathVariable("categoryId") int catId, HttpServletRequest req){
        String cat = req.getParameter("category");
        acs.updateCat(catId , cat);
        return "redirect:/";
    }
}