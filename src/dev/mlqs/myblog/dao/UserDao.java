package dev.mlqs.myblog.dao;

import dev.mlqs.myblog.model.User;

public interface UserDao {
    //boolean register(String username, String password);
    boolean updatePassword (String username, String old_password, String new_password);
    boolean updateUser (String username, String email);
    User login(String username, String password);
}
