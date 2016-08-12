package com.ps.config;

import com.ps.ents.Pet;
import com.ps.mock.MockTemplateConfig;
import com.ps.services.impl.SimplePetService;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by iuliana.cosmina on 4/17/16.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {JdbcRepoConfig.class, ServiceConfig.class, MockTemplateConfig.class})
public class TestAppConfig3 {

    public static final Long PET_ID = 1L;

    // mocking the database
    @Autowired
    JdbcTemplate jdbcTemplate;

    // tested object
    @Autowired
    SimplePetService simplePetService;

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();


    //positive test, we know that a Pet with ID=1 exists
    @Test
    public void findByIdPositive() {
        Mockito.when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyLong())).thenReturn(new Pet());
        Pet pet = simplePetService.findById(PET_ID);
        assertNotNull(pet);
    }
}
