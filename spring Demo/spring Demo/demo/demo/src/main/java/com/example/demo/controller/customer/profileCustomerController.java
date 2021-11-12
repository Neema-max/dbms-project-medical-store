package com.example.demo.controller.customer;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.model.customer;
import com.example.demo.service.authService;
import com.example.demo.service.customer.customerEditProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class profileCustomerController {
    @Autowired
    private authService auth;
    @Autowired
    private customerEditProfile cep;
    @GetMapping("/customer-profile")
    public String customerProfileDisplay(Model model , HttpSession session) {
        if(!auth.isCustomer(session)){
            return "redirect:/";
        }
        customer c = auth.getCustomer(session);
        model.addAttribute("user", c);
        return "auth/customer/profile";
    }
    @PostMapping("/custchangePass")
    public ResponseEntity<?> changePass(HttpSession session , HttpServletRequest req){
        Hashtable<String,String> data = new Hashtable<String,String>();
        customer c = auth.getCustomer(session);
        String newPass = req.getParameter("newpass").toString().trim();
        String oldPass = req.getParameter("oldpass").toString().trim();
        int x = auth.checkCredAndPost(c.getUsername(), oldPass);
        if(x==3){
            cep.updatePass(newPass,c.getCustomerId());
            data.put("result", "success");
        return ResponseEntity.ok(data);
        }
        data.put("result", "err");
        data.put("error","wrong Password");
        return ResponseEntity.ok(data);
    }
}