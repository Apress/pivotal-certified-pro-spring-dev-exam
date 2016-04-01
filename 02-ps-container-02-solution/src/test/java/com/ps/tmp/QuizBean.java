package com.ps.tmp;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 3/28/16.
 */
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class QuizBean {

    public QuizBean() {
        System.out.println(" it's time that we say it");
    }
}
