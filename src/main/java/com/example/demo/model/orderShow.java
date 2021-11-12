package com.example.demo.model;

import java.util.List;

import com.example.demo.model.itemShow.orderItemShow;

public class orderShow {
    private List<orderItemShow> orderItems;
    private order order;
    


    /**
     * @return List<orderItemShow> return the orderItems
     */
    public List<orderItemShow> getOrderItems() {
        return orderItems;
    }

    /**
     * @param orderItems the orderItems to set
     */
    public void setOrderItems(List<orderItemShow> orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * @return order return the order
     */
    public order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(order order) {
        this.order = order;
    }

}