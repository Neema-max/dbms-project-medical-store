package com.example.demo.repository.admin;

import com.example.demo.model.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class employeeAddingRepository {
    @Autowired
    private JdbcTemplate template;
    public int getLastEmployeeId(){
        String sql = "select max(empId) from employee"; 
        Integer x =  (int)template.queryForList(sql).get(0).get("max(empId)");
        return x;
    }
    public void addEmployee(employee a){
        String  sql = "insert into employee values(?,?,?,?,?,?,?,?,?,?)";
        template.update(
            sql,
            a.getEmpId(),
            a.getName(),
            a.getAddress(),
            a.getEmail(),
            a.getUsername(),
            a.getPassword(),
            a.getPhoneNo(),
            a.getDob(),
            a.getAge(),
            a.getImage()
        );
    }
}