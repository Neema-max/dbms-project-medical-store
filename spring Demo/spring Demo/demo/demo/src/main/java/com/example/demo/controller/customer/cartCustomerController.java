package com.example.demo.controller.customer;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.service.customer.customerCartServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller 
public class cartCustomerController {
    @Autowired
    private customerCartServ ccs;
    @PostMapping("/updateCartItem")
    public ResponseEntity<?> updateCartItem(HttpServletRequest req  , HttpSession session){
        Hashtable<String,String> data = new Hashtable<String,String>();
        int itemId  = Integer.parseInt(req.getParameter("id").toString());
        int quantity =  Integer.parseInt(req.getParameter("quantity").toString());
        int cid = Integer.parseInt(session.getAttribute("userId").toString());
        ccs.updateCart(itemId , quantity, cid);
        data.put("result" , "success");
        return ResponseEntity.ok(data);
    }
}