package com.example.demo.service.admin;

import com.example.demo.model.employee;
import com.example.demo.repository.admin.employeeAddingRepository;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Service
public class employeeAddingService {
    @Autowired
    private employeeAddingRepository ear;
    public void addEmployee(employee e){
        ear.addEmployee(e);
    }
}