package com.ps.repo.stub;

import com.ps.ents.Review;
import com.ps.repos.ReviewRepo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class StubReviewRepo implements ReviewRepo {

    private Map<Long, Review> reviewMap = new HashMap<>();

    @Override
    public void save(Review review) {
        if (review.getId() != null) {
            reviewMap.put(review.getId(), review);
        }
    }

    @Override
    public void delete(Review review) {
        reviewMap.remove(review.getId());
    }

    @Override
    public void deleteById(Long reviewId) {
        reviewMap.remove(reviewId);
    }

    @Override
    public Review findById(Long reviewId) {
        return reviewMap.get(reviewId);
    }
}
