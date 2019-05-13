package dev.mlqs.myblog.ajax;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mlqs.myblog.dao.UserDao;
import dev.mlqs.myblog.daoImpl.UserDaoImpl;
import dev.mlqs.myblog.model.User;
import dev.mlqs.myblog.service.AdminService;
import dev.mlqs.myblog.service.ArticleService;
import dev.mlqs.myblog.service.TagService;
import dev.mlqs.myblog.utils.SideInfoUtils;
import dev.mlqs.myblog.utils.StringUtils;
import dev.mlqs.myblog.utils.ConfigUtils;

@WebServlet("/data")
public class AdminDataServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendError(403);
            return;
        }
        String op = request.getParameter("op");
        AdminService as = AdminService.getInstance();

        switch (op) {
            case "edit_article":
                SideInfoUtils.setUp(request, this);
                String a_id1 = request.getParameter("article_id");
                request.setAttribute("edit_article", as.getArticle(a_id1));
                ArticleService ase = ArticleService.getInstance();
                Map sort_count = ase.getSortAndCount();
                request.setAttribute("sort_count", sort_count);
                TagService tg = TagService.getInstance();
                List all_tag = tg.getAllTag();
                request.setAttribute("all_tag", all_tag);
                request.getRequestDispatcher("/admin/edit.jsp").forward(request, response);
                break;
            case "delete_article":
                String a_id2 = request.getParameter("article_id");
                as.delteArticle(a_id2);
                break;
            case "sort_update":
                String old_sort = StringUtils.pareCode(request.getParameter("old_sort"));
                String new_sort = StringUtils.pareCode(request.getParameter("new_sort"));
                if (!old_sort.equals(new_sort)) {
                    as.updateSort(old_sort, new_sort);
                }
                break;
            case "sort_delete":
                String sort = StringUtils.pareCode(request.getParameter("sort"));
                as.deleteSort(sort);
                break;
            case "tag_update":
                String old_tag = StringUtils.pareCode(request.getParameter("old_tag"));
                String new_tag = StringUtils.pareCode(request.getParameter("new_tag"));
                if (!old_tag.equals(new_tag)) {
                    as.updateTag(old_tag, new_tag);
                }
                break;
            case "tag_delete":
                String tag = StringUtils.pareCode(request.getParameter("tag"));
                as.deleteTag(tag);
                break;
            case "update_config":
                for (Map.Entry<String, String[]> entry : ConfigUtils.getConfigUtils(this).des_map.entrySet()) {
                    ConfigUtils.getConfigUtils(this).setValue(entry.getKey(), request.getParameter(entry.getKey()));
                }
                ConfigUtils.getConfigUtils(this).save();
                ConfigUtils.getConfigUtils(this).refresh();
                String site = "/admin";
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
                break;
            case "update_user":
                UserDao userDao1 = UserDaoImpl.getInstance();
                if (userDao1.updateUser(
                        ((User)request.getSession().getAttribute("user")).getUser_name(),
                        request.getParameter("email"))) {
                    User user = ((User)request.getSession().getAttribute("user"));
                    user.setEmail(request.getParameter("email"));
                    request.getSession().setAttribute("user", user);
                }
                break;
            case "update_password":
                UserDao userDao2 = UserDaoImpl.getInstance();
                if (userDao2.updatePassword(
                        ((User)request.getSession().getAttribute("user")).getUser_name(),
                        request.getParameter("old_password"),
                        request.getParameter("password")))
                    response.getWriter().print("success");
                else
                    response.getWriter().print("failure");
                break;
            default:
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
