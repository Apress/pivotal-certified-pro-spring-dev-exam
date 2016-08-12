package com.ps.repos.impl;

import com.ps.base.ResponseStatus;
import com.ps.ents.Response;
import com.ps.ents.User;
import com.ps.repos.ResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */
@Repository("responseRepo")
public class JdbcResponseRepo extends JdbcAbstractRepo<Response>  implements ResponseRepo{

    @Autowired
    public JdbcResponseRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Set<Response> findAllForUser(User user) {
        return null;
    }

    @Override
    public Set<Response> findAllByStatus(ResponseStatus status) {
        return null;
    }
}
