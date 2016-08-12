package com.ps.repos.impl;

import com.ps.ents.User;
import com.ps.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */
@Repository("userRepo")
public class JdbcUserRepo implements UserRepo {

    private DataSource dataSource;

    public JdbcUserRepo() {
    }

    @Autowired
    public JdbcUserRepo(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Set<User> findAll() {
        Set<User> userSet = new HashSet<>();
        String sql = "select u.ID as ID, u.USERNAME as USERNAME, u.EMAIL as EMAIL, u.PASSWORD as PASSWORD from P_USER u ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            userSet = mapUsers(rs);
        } catch (SQLException e) {
            throw new RuntimeException("User not found!", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return userSet;
    }


    @Override
    public Set<User> findAllByUserName(String username, boolean exactMatch) {
        Set<User> userSet = new HashSet<>();
        String sql = "select u.ID as ID, u.USERNAME as USERNAME, u.EMAIL as EMAIL, u.PASSWORD as PASSWORD from P_USER u where ";
        if (exactMatch) {
            sql += "u.USERNAME= ?";
        } else {
            sql += "u.USERNAME like '%' || ? || '%'";
        }
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            userSet = mapUsers(rs);
        } catch (SQLException e) {
            throw new RuntimeException("User not found!", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return userSet;
    }

    @Override
    public User findById(Long id) {
        String sql = "select u.ID as ID, u.USERNAME as USERNAME, u.EMAIL as EMAIL, u.PASSWORD as PASSWORD from P_USER u where u.ID = ?";
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Set<User> userSet = mapUsers(rs);
            if (!userSet.isEmpty()) {
                return userSet.iterator().next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("User not found!", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return null;
    }


    @Override
    public int updatePassword(Long userId, String newPass) {
        String sql = "update P_USER set password=? where ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, newPass);
            ps.setLong(2, userId);
            ps.executeUpdate();
            conn.commit();
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException("User could not be updated", e);
        } finally {
            if (ps != null) {
                try {
                    // Close to prevent database cursor exhaustion
                    ps.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    // Close to prevent database connection exhaustion
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }


    @Override
    public int updateUsername(Long userId, String username) {
        //not needed yet
        return 0;
    }

    private Set<User> mapUsers(ResultSet rs) throws SQLException {
        Set<User> userSet = new HashSet<>();
        User user = null;
        while (rs.next()) {
            user = new User();
            // set internal entity identifier (primary key)
            user.setId(rs.getLong("ID"));
            user.setUsername(rs.getString("USERNAME"));
            user.setEmail(rs.getString("EMAIL"));
            user.setPassword(rs.getString("PASSWORD"));
            userSet.add(user);
        }
        return userSet;
    }
}
