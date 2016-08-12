package com.ps.repo.services;

import com.ps.base.UserType;
import com.ps.ents.Request;
import com.ps.ents.Review;
import com.ps.ents.User;
import com.ps.repos.ReviewRepo;
import com.ps.services.impl.SimpleReviewService;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.ps.util.TestObjectsBuilder.buildUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by iuliana.cosmina on 4/17/16.
 */
public class SimpleReviewServiceTest {
    public static final Long REVIEW_ID = 1L;

    private ReviewRepo reviewMockRepo = mock(ReviewRepo.class);

    private SimpleReviewService  simpleReviewService;


    @Before
    public void setUp(){
        simpleReviewService = new SimpleReviewService();
        simpleReviewService.setRepo(reviewMockRepo);
    }

    @Test
    public void findByIdPositive() {
        Review review = new Review();
        review.setId(REVIEW_ID);
        when(reviewMockRepo.findById(REVIEW_ID)).thenReturn(review);

        Review result = simpleReviewService.findById(REVIEW_ID);
        assertNotNull(result);
        assertEquals(review.getId(), result.getId());
    }

    @Test
    public void findByUserPositive() {
        User user = buildUser("gigi@gmail.com", "1!2#tre", UserType.OWNER);
        Request req = new Request();
        req.setUser(user);
        Review review = new Review();
        review.setRequest(req);

        Set<Review> reviewSet = new HashSet<>();
        reviewSet.add(review);

        when(reviewMockRepo.findAllForUser(user)).thenReturn(reviewSet);
        Set<Review> result = simpleReviewService.findAllByUser(user);
        verify(reviewMockRepo, times(1)).findAllForUser(user);
        assertEquals(result.size(), 1);
    }
}
