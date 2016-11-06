package com.ps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by iuliana.cosmina on 8/23/16.
 */
@RestController
public class CtxController {

    public static final String INTRO = "\"Ws-Boot up and running! \"";

    @Autowired
    ApplicationContext ctx;

    @RequestMapping("/check")
    @ResponseBody
    public String index() {
        StringBuilder sb = new StringBuilder("{message: ");

        sb.append(INTRO);

        sb.append("}");
        return sb.toString();
    }

}
