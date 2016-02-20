package com.ps.service;

import com.ps.base.ReviewGrade;
import com.ps.ents.Review;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class SimpleReviewService implements ReviewService {
    @Override
    public Review createReview(ReviewGrade grade, String details) {
        Review review = new Review();
        review.setGrade(grade);
        review.setDetails(details);
        return review;
    }
}
