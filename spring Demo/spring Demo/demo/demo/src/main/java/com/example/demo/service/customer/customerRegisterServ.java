package com.example.demo.service.customer;


import com.example.demo.model.customer;
import com.example.demo.repository.customer.customerRegisterRepo;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Service
public class customerRegisterServ {
    @Autowired 
    private customerRegisterRepo crr;
    public void addCustomer(customer c){
        crr.addCustomer(c);
    }
    
    
}