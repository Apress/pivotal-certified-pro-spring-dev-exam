package com.ps.services;

import com.ps.base.ReviewGrade;
import com.ps.ents.Review;
import com.ps.repos.ResponseRepo;
import com.ps.repos.ReviewRepo;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class SimpleReviewService implements ReviewService {

    private ReviewRepo reviewRepo;

    @Override
    public Review createReview(ReviewGrade grade, String details) {
        Review review = new Review();
        review.setGrade(grade);
        review.setDetails(details);
        reviewRepo.save(review);
        return review;
    }

    @Override
    public Review save(Review review) {
        reviewRepo.save(review);
        return review;
    }

    //                setters
    public void setReviewRepo(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }
}
