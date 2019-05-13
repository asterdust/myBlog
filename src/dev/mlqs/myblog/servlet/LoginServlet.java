package dev.mlqs.myblog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mlqs.myblog.utils.LoginUtils;
import dev.mlqs.myblog.utils.SecurityUtils;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (LoginUtils.login(request)) {
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "/");
        }
        else {
            request.getRequestDispatcher("/notif?ec=1").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
