package com.ps.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by iuliana.cosmina on 8/23/16.
 */
@Controller
public class CtxController {

    public static final String INTRO = "Hello there dear developer, here are the beans you were looking for: </br>";

    @Autowired
    ApplicationContext ctx;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        StringBuilder sb = new StringBuilder("<html><body>");

        sb.append(INTRO);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            sb.append("</br>").append(beanName);
        }
        sb.append("</body></htm>");
        return sb.toString();
    }

    @RequestMapping("/home")
    public String home(ModelMap model) {
        model.put("bogus", "data");
        return "home";
    }

}
