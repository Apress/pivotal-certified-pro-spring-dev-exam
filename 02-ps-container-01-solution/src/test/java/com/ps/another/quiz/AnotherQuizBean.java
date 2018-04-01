package com.ps.another.quiz;

import com.ps.quiz.QuizBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 11/18/16.
 */
@Component
public class AnotherQuizBean {

    @Autowired
    QuizBean quizBean;
}
