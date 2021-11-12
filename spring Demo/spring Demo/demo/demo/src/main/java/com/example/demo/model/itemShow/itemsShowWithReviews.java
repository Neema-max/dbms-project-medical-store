package com.example.demo.model.itemShow;

import java.util.List;

import com.example.demo.model.reviewsShow;

public class itemsShowWithReviews extends itemsShow {
    private List<reviewsShow> reviewShow;

    /**
     * @return List<reviewsShow> return the reviewShow
     */
    public List<reviewsShow> getReviewShow() {
        return reviewShow;
    }

    /**
     * @param reviewShow the reviewShow to set
     */
    public void setReviewShow(List<reviewsShow> reviewShow) {
        this.reviewShow = reviewShow;
    }

}