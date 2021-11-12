package com.example.demo.service.admin;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.items;
import com.example.demo.model.order;
import com.example.demo.model.orderShow;
import com.example.demo.model.orderToItems;
import com.example.demo.model.itemShow.orderItemShow;
import com.example.demo.repository.authRepo;
import com.example.demo.repository.admin.adminOrderRepo;
import com.example.demo.repository.customer.customerOrderRepo;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service
public class adminOrderService {
    @Autowired
    private adminOrderRepo aor;
    @Autowired
    private customerOrderRepo cor;
    @Autowired
    private authRepo auth;
    public List<orderShow> getAllOrders(){
        List<orderShow> ll = new ArrayList<orderShow>();
        List<order> orders = aor.getAllOrders();
        for(int i=0;i<orders.size();i++){
            order o = orders.get(i);
            orderShow os = new orderShow();
            List<orderToItems> items = cor.getItemsByOrderId(o.getOrderId());
            List<orderItemShow> ot = new ArrayList<orderItemShow>();
            os.setOrder(o);
            for(int j=0;j<items.size();j++){
                orderToItems oti = items.get(j);
                int units = oti.getUnits();
                int itemId = oti.getItemId();
                orderItemShow ois = new orderItemShow();
                items item = auth.getItem(itemId);
                ois.setCategory(auth.getCatById(item.getCategoryId()));
                ois.setItem(item);
                ois.setItemImage(auth.getItemImage(itemId));
                ois.setUnits(units);
                ois.setPrice(oti.getPrice());
                ot.add(ois);
            }
            os.setOrderItems(ot);
            ll.add(os);
        }
        return ll;
    }
    public void increaseOrderStatus(int orderId){
        aor.increaseOrderStatus(orderId);
    }
    public void cancelOrder(int orderId){
        aor.cancelOrder(orderId);
    }
}