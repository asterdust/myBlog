package dev.mlqs.myblog.dao;

import dev.mlqs.myblog.model.User;

public interface UserDao {
    boolean register(String username, String password);
    User login(String username, String password);
}