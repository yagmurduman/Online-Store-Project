package com.onlineStore.controller;


import com.onlineStore.controller.api.ReviewControllerApi;
import com.onlineStore.model.Review;
import com.onlineStore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController (value = "/review")
public class ReviewController implements ReviewControllerApi {

    @Autowired
    ReviewService reviewService;

    @Override
    public ResponseEntity<List<Review>> getAllReviewsToApprove() {
        List<Review> reviews = reviewService.getAllReviewsToApprove();
        if(reviews == null){
            return new ResponseEntity<>((List<Review>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Review> addReviewToProduct(Review review) {
        reviewService.addReviewToProduct(review);
        return new ResponseEntity<> (review, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteRatingById(Integer cId, Integer pId) {
        reviewService.deleteReviewById(cId, pId);
        return new ResponseEntity<>("Review is deleted.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Review>> getReviewsByPid(Integer pId) {
        List<Review> reviews = reviewService.getReviewsByPid(pId);
        if(reviews == null){
            return new ResponseEntity<>((List<Review>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Float> getAvgRateOfProductByPid(Integer pId) {
        Float rate = reviewService.getAvgRateOfProductById(pId);
        if(rate == null){
            return new ResponseEntity<>((Float) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }
}
