package com.example.demo.model;

public class order {
    private int orderId;
    private int customerId;
    private int orderStatus; 
    private String deliveryAdd;
    private String orderDate;
    private int subtotal;
    // 0 waiting
    // 1 placed 
    // 2 packed
    // 3 shipped
    // 4 arrived
    // 5 paid
    // 6 delivered
    // -1 canceled

    /**
     * @return int return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return int return the orderStatus
     */
    public int getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }


    /**
     * @return String return the deliveryAdd
     */
    public String getDeliveryAdd() {
        return deliveryAdd;
    }

    /**
     * @param deliveryAdd the deliveryAdd to set
     */
    public void setDeliveryAdd(String deliveryAdd) {
        this.deliveryAdd = deliveryAdd;
    }


    /**
     * @return String return the orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }


    /**
     * @return int return the subtotal
     */
    public int getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }


    /**
     * @return int return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}