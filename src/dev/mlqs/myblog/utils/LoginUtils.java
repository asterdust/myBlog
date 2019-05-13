package dev.mlqs.myblog.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dev.mlqs.myblog.dao.UserDao;
import dev.mlqs.myblog.daoImpl.UserDaoImpl;
import dev.mlqs.myblog.model.User;

public class LoginUtils {

    public static boolean login(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = SecurityUtils.String2SHA256(request.getParameter("password"), true);

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
            return false;

        UserDao dao = UserDaoImpl.getInstance();
        User user = dao.login(username, password);
        if (user == null)
            return false;

        
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return true;
    }

}
