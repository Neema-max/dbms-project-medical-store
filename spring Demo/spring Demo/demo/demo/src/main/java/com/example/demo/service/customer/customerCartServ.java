package com.example.demo.service.customer;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.example.demo.model.cart;
import com.example.demo.model.items;
import com.example.demo.model.itemShow.orderItemShow;
import com.example.demo.repository.authRepo;
import com.example.demo.repository.customer.customerCartRepo;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@Service
public class customerCartServ {
    @Autowired
    private customerCartRepo ccr;
    @Autowired
    private authRepo auth;
    private int subtotal = 0;
    public List<orderItemShow> getCutomerCart(HttpSession session){
        int customerId = Integer.parseInt(session.getAttribute("userId").toString());
        List<cart> l =  ccr.getCartItems(customerId);
        List<orderItemShow> ll = new ArrayList<orderItemShow>() ;
        for(int i = 0 ;i<l.size();i++){
            orderItemShow ois = new orderItemShow();
            items item = auth.getItem(l.get(i).getItemId());
            int units = l.get(i).getUnits();
            String cat = auth.getCatById(item.getCategoryId());
            if(units <= item.getQuantity()){
                ois.setItem(item);
                ois.setCategory(cat);
                subtotal += item.getSellingPrice()*units;
                ois.setItemImage(auth.getItemImage(item.getItemId()));
                ois.setUnits(units);
                ll.add(ois);
            }
        }
        return ll;
    }

    public int getSubtotal() {
        int temp = subtotal;
        subtotal = 0;
        return temp;
    }
    public void updateCart(int itemId , int quantity,int cid){
        ccr.updateCart(itemId ,quantity,cid);
    }
}