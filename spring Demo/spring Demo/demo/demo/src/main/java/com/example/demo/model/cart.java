package com.example.demo.model;

public class cart {
    private int customerId;
    private Integer itemId;
    private Integer units;
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
    /**
     * @return Integer return the itemId
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }


    /**
     * @return Integer return the units
     */
    public Integer getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(Integer units) {
        this.units = units;
    }

}