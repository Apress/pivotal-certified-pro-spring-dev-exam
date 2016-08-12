package com.ps.repos.util;

import com.ps.ents.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by iuliana.cosmina on 7/7/16.
 * Maps a row returned from a query executed on the P_USER table to a User object.
 */
public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("ID");
        String email = rs.getString("EMAIL");
        String username = rs.getString("USERNAME");
        String password = rs.getString("PASSWORD");
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
