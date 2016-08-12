package com.ps.config;

import com.ps.ents.Response;
import com.ps.ents.Review;
import com.ps.repos.PetRepo;
import com.ps.repos.ResponseRepo;
import com.ps.repos.ReviewRepo;
import com.ps.repos.UserRepo;
import com.ps.repos.impl.JdbcAbstractRepo;
import com.ps.repos.impl.JdbcResponseRepo;
import com.ps.repos.impl.JdbcReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by iuliana.cosmina on 3/21/16.
 * Sample class to depict how @Import
 */
@Configuration
@Import({DataSourceConfig.class, UserRepoDSConfig.class})
public class AllRepoConfig {

    @Bean
    public ReviewRepo reviewRepo(){
        return new JdbcReviewRepo();
    }

    @Bean
    public ResponseRepo responseRepo(){
        return new JdbcResponseRepo();
    }

}
