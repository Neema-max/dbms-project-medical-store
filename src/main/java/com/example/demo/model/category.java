package com.example.demo.model;

public class category {
    private int catId;
    private String category;
    private String image;
    public String getImagePath() {
        if (image.equals("#") || image.isEmpty()) return "/user-photos/category/default.png";
        return "/user-photos/category/" + catId + "/" + image;
    }
    /**
     * @return int return the catId
     */
    public int getCatId() {
        return catId;
    }

    /**
     * @param catId the catId to set
     */
    public void setCatId(int catId) {
        this.catId = catId;
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
