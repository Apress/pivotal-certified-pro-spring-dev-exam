package com.ps.repos.impl;

import com.ps.ents.User;
import com.ps.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 6/4/16.
 */
@Repository("userTemplateRepo")
public class JdbcTemplateUserRepo implements UserRepo {

    private RowMapper<User> rowMapper = new UserRowMapper();

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateUserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Set<User> findAll() {
        String sql = "select id, username, email, password from p_user";
        return new HashSet<>(jdbcTemplate.query(sql, rowMapper));
    }

    @Override
    public User findById(Long id) {
        String sql = "select id, email, username,password from p_user where id= ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public Set<User> findAllByUserName(String username, boolean exactMatch) {
        String sql = "select id, username, email, password from p_user where ";
        if (exactMatch) {
            sql += "username= ?";
        } else {
            sql += "username like '%' || ? || '%'";
        }
        return new HashSet<>(jdbcTemplate.query(sql, new Object[]{username}, rowMapper));
    }

    @Override
    public int updatePassword(Long userId, String newPass) {
        String sql = "update p_user set password=? where ID = ?";
        return jdbcTemplate.update(sql, newPass, userId);
    }

    @Override
    public int updateUsername(Long userId, String username) {
        String sql = "update p_user set username=? where ID = ?";
        return jdbcTemplate.update(sql, username, userId);
    }

    /**
     * Maps a row returned from a query executed on the P_USER table to a User object.
     */
    private class UserRowMapper implements RowMapper<User> {

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
}
