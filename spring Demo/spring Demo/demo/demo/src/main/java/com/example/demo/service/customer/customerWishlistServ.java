package com.example.demo.service.customer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.model.items;
import com.example.demo.model.itemShow.itemsShow;
import com.example.demo.repository.authRepo;
import com.example.demo.repository.customer.customerWishlistRepo;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service
public class customerWishlistServ {
    @Autowired
    private customerWishlistRepo cwr;
    @Autowired
    private authRepo auth;
    public List<itemsShow> getCustomerWishlist(HttpSession session){
        int customerId = Integer.parseInt(session.getAttribute("userId").toString());
        List<Integer> l =  cwr.getwishlistItems(customerId);
        List<itemsShow> ll = new ArrayList<itemsShow>() ;
        for(int i = 0 ;i<l.size();i++){
            itemsShow is = new itemsShow();
            items item = auth.getItem(l.get(i)) ;
            String cat = auth.getCatById(item.getCategoryId());
            is.setCategory(cat);
            is.setItem(item);
            is.setItemImage(auth.getItemImage(item.getItemId()));
            ll.add(is);
        }
        return ll;
    }
}