package dev.mlqs.myblog.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import dev.mlqs.myblog.dao.UserDao;
import dev.mlqs.myblog.db.C3P0Connection;
import dev.mlqs.myblog.model.User;
import dev.mlqs.myblog.utils.DBUtils;

public class UserDaoImpl implements UserDao {

    private Connection conn;

    private UserDaoImpl() {
        conn = C3P0Connection.getInstance().getConnection();
    }

    private static UserDao instance;

    public static final UserDao getInstance() {
        if (instance == null) {
            try {
                instance = new UserDaoImpl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    
//    @Override
//    public boolean register(String username, String password) {
//        return false;
//    }

    @Override
    public boolean updatePassword(String username, String old_password, String new_password) {
        if (login(username, old_password) == null) {
            return false;
        }
        String sql = "update t_user set user_password=? where user_name=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, new_password);
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps);
            } catch (SQLException e1) {}
        }
        return false;
    }

    @Override
    public boolean updateUser(String username, String email) {
        String sql = "update t_user set email=? where user_name=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, username);
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps);
            } catch (SQLException e1) {}
        }
        return false;
    }

    @Override
    public User login(String username, String password) {
        User user = null;
        String sql = "select * from t_user where user_name=? and user_password=? ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                Map<String, String> map = new HashMap<>();
                user = new User();
                map.put("user_name", rs.getString("user_name"));
                map.put("user_password", rs.getString("user_name"));
                map.put("user_id", rs.getString("user_id"));
                map.put("email", rs.getString("user_email"));
                try {
                    BeanUtils.populate(user, map);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps, rs);
            } catch (SQLException e1) {}
        }
        return user;
    }
}