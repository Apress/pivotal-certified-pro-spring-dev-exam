package com.ps.start;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.*;


/**
 * Created by iuliana.cosmina on 8/24/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"app.port=9090"})
public class CtxControllerTest {

    @Autowired
    CtxController ctxController;

    private ModelMap model = new ModelMap();


    @Test
    public void textIndex() {
        String result = ctxController.index();
        assertNotNull(result);
        assertTrue(result.contains(CtxController.INTRO));
    }

    @Test
    public void textHome() {
        String result = ctxController.home(model);
        assertEquals("home", result);

        String modelData = (String)model.get("bogus");
        assertEquals("data", modelData);
    }

}
