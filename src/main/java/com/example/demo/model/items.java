package com.example.demo.model;

public class items {
    private int itemId;
    private String itemDescription;
    private String itemName;
    private int sellingPrice;
    private int buyingPrice;
    private int categoryId;
    private int quantity;
    /**
     * @return int return the itemId
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * @return String return the itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * @param itemDescription the itemDescription to set
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * @return String return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    /**
     * @return int return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return int return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
     * @return int return the sellingPrice
     */
    public int getSellingPrice() {
        return sellingPrice;
    }

    /**
     * @param sellingPrice the sellingPrice to set
     */
    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * @return int return the buyingPrice
     */
    public int getBuyingPrice() {
        return buyingPrice;
    }

    /**
     * @param buyingPrice the buyingPrice to set
     */
    public void setBuyingPrice(int buyingPrice) {
        this.buyingPrice = buyingPrice;
    }    
}



