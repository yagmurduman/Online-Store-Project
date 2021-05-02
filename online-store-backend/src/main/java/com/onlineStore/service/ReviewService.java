package com.onlineStore.service;

import com.onlineStore.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviewsToApprove ();
    void addReviewToProduct(Review review);
    void deleteReviewById (Integer cId, Integer pId);
    List<Review> getReviewsByPid (Integer pId);
    Float getAvgRateOfProductById (Integer pId);

}
