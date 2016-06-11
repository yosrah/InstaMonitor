package com.yosrahibrahim.instamonitor.reviews.model;

/**
 * Created by Yosrah Ibrahim on 6/10/2016.
 *
 * model for review
 */
public class Review {

    /**
     * review text
     */
    private String review;

    /**
     * reviewer company position
     */
    private String reviewer;

    /**
     * id of company logo drawable
     */
    private int logoId;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public int getLogoId() {
        return logoId;
    }

    public void setLogoId(int logoId) {
        this.logoId = logoId;
    }
}
