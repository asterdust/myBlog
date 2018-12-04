package dev.mlqs.myblog.servlet;

import dev.mlqs.myblog.utils.SideInfoUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/NotifServlet")
public class NotifServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ec = request.getParameter("ec");
        String message, back_color;
        switch (ec) {
            case "0":
                message = "请先登录！";
                back_color = "red";
                break;
            case "1":
                message = "登录失败，账户不存在或密码错误";
                back_color = "red";
                break;
            case "2":
                message = "退出登录成功";
                back_color = "green";
                break;
            case "403":
                message = "拒绝访问";
                back_color = "red";
                break;
            case "404":
                message = "页面不存在了";
                back_color = "red";
                break;
            default:
                message = "未知错误";
                back_color = "red";
                break;
        }

        request.setAttribute("message", message);
        request.setAttribute("back_color", back_color);
        SideInfoUtils.setUp(request, this);
        request.getRequestDispatcher("/notif.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
