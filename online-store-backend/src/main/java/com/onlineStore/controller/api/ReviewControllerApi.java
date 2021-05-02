package com.onlineStore.controller.api;

import com.onlineStore.model.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ReviewControllerApi {

    @GetMapping(value="review/get-all-reviews-to-approve")
    ResponseEntity<List<Review>> getAllReviewsToApprove();

    @PostMapping(value="review/add-review")
    ResponseEntity<Review> addReviewToProduct (@RequestBody Review review);

    @DeleteMapping(value="review/delete-review/cId={cId}&pId={pId}")
    ResponseEntity<String> deleteRatingById(@PathVariable Integer cId, @PathVariable Integer pId);

    @GetMapping(value="review/get-reviews-by-pId={pId}")
    ResponseEntity<List<Review>> getReviewsByPid(@PathVariable Integer pId);

    @GetMapping(value="review/get-avg-rate-by-pId={pId}")
    ResponseEntity<Float> getAvgRateOfProductByPid(@PathVariable Integer pId);
}
