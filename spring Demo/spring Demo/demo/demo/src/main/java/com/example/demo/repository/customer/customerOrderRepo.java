package com.example.demo.repository.customer;

import java.util.List;

import com.example.demo.model.order;
import com.example.demo.model.orderToItems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class customerOrderRepo {
    @Autowired
    private JdbcTemplate template;
    public void saveOrder(order o){
        String sql = "insert into orders values(?,?,?,?,?,?)";
        template.update(sql, o.getOrderId() , o.getCustomerId() , o.getOrderStatus(),o.getDeliveryAdd(),o.getOrderDate(),o.getSubtotal());
    }
    public void saveoti(orderToItems oti){
        String sql = "insert into orderToitems values(?,?,?,?)";
        template.update(sql, oti.getOrderId() , oti.getItemId() , oti.getUnits(), oti.getPrice());
    }
    public List<order> getOrders(int cid){
        String sql = "select * from orders where customerId = ? ";
        return  template.query(sql,new BeanPropertyRowMapper<>(order.class),cid);
    }
    public List<orderToItems> getItemsByOrderId(int oid){
        String sql = "select * from orderToItems where orderId = ?";
        return  template.query(sql,new BeanPropertyRowMapper<>(orderToItems.class),oid);
    }
    public int lastOrderId(){
        String sql = "select max(orderId) from orders"; 
        Integer x =  (int)template.queryForList(sql).get(0).get("max(orderId)");
        return x;
    }
    public void updateItemQuantity(int units , int itemid){
        String sql = "update items set quantity = quantity - ? where itemId = ?";
        template.update(sql,units , itemid);
    }
}