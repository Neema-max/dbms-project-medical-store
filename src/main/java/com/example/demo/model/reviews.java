package com.example.demo.model;

public class reviews {
    private int itemId;
    private int customerId;
    private String reviewDescription;
    private float rating;

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
     * @return String return the reviewDescription
     */
    public String getReviewDescription() {
        return reviewDescription;
    }

    /**
     * @param reviewDescription the reviewDescription to set
     */
    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    /**
     * @return float return the rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(float rating) {
        this.rating = rating;
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