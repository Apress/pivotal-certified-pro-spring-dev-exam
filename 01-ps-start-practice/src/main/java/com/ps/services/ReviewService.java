package com.ps.services;

import com.ps.base.ReviewGrade;
import com.ps.ents.Review;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public interface ReviewService {

    Review createReview(ReviewGrade grade, String details);

}
