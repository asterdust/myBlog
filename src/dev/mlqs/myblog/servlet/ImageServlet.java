package dev.mlqs.myblog.servlet;

import dev.mlqs.myblog.utils.ConfigUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/random_images")
public class ImageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String site = "https://picsum.photos/1600/900/?random";
        String t = ConfigUtils.getConfigUtils(this).des_map.get("img_random_url")[2];
        if (!t.equals(""))
            site = t;

        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}
