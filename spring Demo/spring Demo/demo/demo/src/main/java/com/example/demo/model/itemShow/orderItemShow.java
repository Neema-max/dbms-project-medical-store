package com.example.demo.model.itemShow;

public class  orderItemShow extends itemsShow {
    private int units;
    private int price;
    public int getUnits() {
        return units;
    }
    public void setUnits(int units) {
        this.units = units;
    }

    /**
     * @return int return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

}