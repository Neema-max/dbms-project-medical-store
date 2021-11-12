package com.example.demo.model.itemShow;

import java.util.List;

import com.example.demo.model.itemImage;
import com.example.demo.model.items;


public class itemsShow  {
    private items item;
    private String category;
    private List<itemImage> itemImage;




    /**
     * @return items return the item
     */
    public items getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(items item) {
        this.item = item;
    }

    /**
     * @return List<itemImage> return the itemImage
     */
    public List<itemImage> getItemImage() {
        return itemImage;
    }

    /**
     * @param itemImage the itemImage to set
     */
    public void setItemImage(List<itemImage> itemImage) {
        this.itemImage = itemImage;
    }


    /**
     * @return String return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

}