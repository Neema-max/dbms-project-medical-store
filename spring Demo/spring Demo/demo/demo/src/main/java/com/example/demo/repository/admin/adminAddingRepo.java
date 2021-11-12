package com.example.demo.repository.admin;
import com.example.demo.model.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class adminAddingRepo {
    @Autowired
    private JdbcTemplate template;
    public int getLastAdminID(){
        String sql = "select max(adminId) from admin"; 
        Integer x =  (int)template.queryForList(sql).get(0).get("max(adminId)");
        return x;
    }
    public void addAdmin(admin a){
        String  sql = "insert into admin values(?,?,?,?,?,?,?,?,?,?)";
        template.update(
            sql,
            a.getAdminId(),
            a.getName(),
            a.getUsername(),
            a.getPassword(),
            a.getPhoneNo(),
            a.getAddress(),
            a.getEmail(),
            a.getDob(),
            a.getAge(),
            a.getImage()
        );
    }
}