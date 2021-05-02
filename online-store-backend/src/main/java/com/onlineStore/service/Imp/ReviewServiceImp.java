package com.onlineStore.service.Imp;

import com.onlineStore.model.Review;
import com.onlineStore.repository.ReviewRepository;
import com.onlineStore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public void addReviewToProduct(Review review) {
        reviewRepository.addReviewToProduct(review);
    }

    @Override
    public void deleteReviewById(Integer cId, Integer pId) {
        reviewRepository.deleteReviewById(cId, pId);
    }

    @Override
    public List<Review> getReviewsByPid(Integer pId) {
        return reviewRepository.getReviewsByPid(pId);
    }

    @Override
    public Float getAvgRateOfProductById(Integer pId) {
        return reviewRepository.getAvgRateOfProductById(pId);
    }

    @Override
    public List<Review> getAllReviewsToApprove() {return reviewRepository.getAllReviewsToApprove();};
}
