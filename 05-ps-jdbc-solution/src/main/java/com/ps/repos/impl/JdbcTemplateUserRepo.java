package com.ps.repos.impl;

import com.ps.ents.User;
import com.ps.repos.UserRepo;
import com.ps.repos.util.UserRowMapper;
import com.ps.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    public void htmlAllByName(String name) {
        String sql = "select id, username, email from p_user where username= ?";
        jdbcTemplate.query(sql, new HTMLUserRowCallbackHandler(System.out), name);
    }

    private class HTMLUserRowCallbackHandler implements RowCallbackHandler {

        private PrintStream out;

        public HTMLUserRowCallbackHandler(PrintStream out) {
            this.out = out;
        }

        @Override
        public void processRow(ResultSet rs) throws SQLException {
            StringBuilder htmlSb = new StringBuilder("<p>user ID: " + rs.getLong("ID") + "</p></br>\n")
                    .append("<p>username: " + rs.getString("USERNAME") + "</p></br>\n")
                    .append("<p>email: " + rs.getString("EMAIL") + "</p></br>");
            out.print(htmlSb.toString());
        }
    }

    @Override
    public List<Map<String, Object>> findAllAsMaps() {
        String sql = "select * from p_user";
        return jdbcTemplate.queryForList(sql);
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

    @Override
    public String findUsernameById(Long id) {
        String sql = "select email from p_user where ID =?";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    @Override
    public Map<String, Object> findByIdAsMap(Long id) {
        String sql = "select * from p_user where id= ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override
    public int countUsers() {
        String sql = "select count(*) from p_user";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int createUser(Long userId, String username, String password, String email) {
        return jdbcTemplate.update(
                "insert into p_user(ID, USERNAME, PASSWORD, EMAIL) values(?,?,?,?)",
          userId, username, password, email
        );
    }

    @Override
    public int deleteById(Long userId) {
        return jdbcTemplate.update(
                "delete from p_user where id =? ",
                userId);
    }

    @Override
    public int createTable(String name) {
        jdbcTemplate.execute("create table " + name+ " (id integer, name varchar2)" );
        String sql = "select count(*) from " + name;
        return jdbcTemplate.queryForObject(sql, Integer.class);

    }

    @Override
    public Pair extractPair() {
        String sql = "select id, username, email from p_user";
        return jdbcTemplate.query(sql, new PairResultExtractor());
    }

    private class PairResultExtractor implements ResultSetExtractor<Pair> {
        @Override
        public Pair extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<User> users = new ArrayList<>();
            User user = null;
            while (rs.next()) {
                user = new User();
                // set internal entity identifier (primary key)
                user.setId(rs.getLong("ID"));
                user.setUsername(rs.getString("USERNAME"));
                user.setEmail(rs.getString("EMAIL"));
                users.add(user);
            }
            if (users.size() >= 2) {
                return Pair.of(users.get(0), users.get(users.size() - 1));
            }
            return null;
        }


    }

}
