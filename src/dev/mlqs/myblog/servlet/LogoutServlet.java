package dev.mlqs.myblog.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("user", null);

        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "/notif?ec=2");
    }

}
