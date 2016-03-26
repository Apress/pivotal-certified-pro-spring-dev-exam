package com.ps.services.impl;

import com.ps.base.ReviewGrade;
import com.ps.ents.Review;
import com.ps.repos.ReviewRepo;
import com.ps.services.ReviewService;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class SimpleReviewService extends SimpleAbstractService<Review> implements ReviewService {

    private ReviewRepo repo;

    public SimpleReviewService(ReviewRepo reviewRepo) {
        this.repo = reviewRepo;
    }

    @Override
    public Review createReview(ReviewGrade grade, String details) {
        Review review = new Review();
        review.setGrade(grade);
        review.setDetails(details);
        repo.save(review);
        return review;
    }

    //                setters & getters
    @Override
    public ReviewRepo getRepo() {
        return repo;
    }
}
