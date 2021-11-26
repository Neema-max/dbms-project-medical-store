package com.example.demo.model;

public class itemImage {
    private int itemId;
    private String image;
    
    public String getImagePath() {
        if (image.equals("#") || image.isEmpty() ) return "https://mybucket-user-photos.s3.ap-south-1.amazonaws.com/user-photos/items/default.jfif";
        return "https://mybucket-user-photos.s3.ap-south-1.amazonaws.com/user-photos/items/" + itemId + "/" + image;
    }
    

  

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
     * @return String return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

}
