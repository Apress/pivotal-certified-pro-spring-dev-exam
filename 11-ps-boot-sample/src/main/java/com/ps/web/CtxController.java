package com.ps.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by iuliana.cosmina on 8/23/16.
 */
@RestController
public class CtxController {

    @Autowired
    ApplicationContext ctx;

    @RequestMapping("/")
    public String index() {
        StringBuilder sb = new StringBuilder("<html><body>");

        sb.append("Hello there dear developer, here are the beans you were looking for: </br>");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            sb.append("</br>").append(beanName);
        }
        sb.append("</body></htm>");
        return sb.toString();
    }

}
