package com.example.demo.service.employee;

import javax.servlet.http.HttpSession;

import com.example.demo.model.employee;
import com.example.demo.repository.authRepo;
import com.example.demo.repository.employee.employeeRepo;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Service
public class employeeLoginServ {
    @Autowired
    private authRepo auth;
    @Autowired
    private employeeRepo emp;
    public void login(HttpSession session, String username , String customerId){
        session.setAttribute("userId", customerId);
    }
    public int getEmployeeId(String username){
        employee e = auth.findEmployee(username); 
        return e.getEmpId();
    }
    public void logout(HttpSession session){
        session.removeAttribute("userId");
    }
    public void updatePass(String newPass,int cid){
        emp.updatePass(newPass,cid);
    }
    public void editProfile(employee c){
        emp.editProfile(c);
    }
}