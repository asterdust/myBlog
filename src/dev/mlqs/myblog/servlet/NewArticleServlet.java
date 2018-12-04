package dev.mlqs.myblog.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mlqs.myblog.model.Article;
import dev.mlqs.myblog.service.AdminService;
import dev.mlqs.myblog.service.ArticleService;
import dev.mlqs.myblog.service.TagService;

/**
 * Servlet implementation class NewArticleServlet
 */
@WebServlet("/NewArticleServlet")
public class NewArticleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendError(403);
			return;
		}
		AdminService as = AdminService.getInstance();
		Article result = as.addArticle(request);
		request.setAttribute("article", result);
		ArticleService ase = ArticleService.getInstance();
		Map sort_count = ase.getSortAndCount();
		request.setAttribute("sort_count", sort_count);
		TagService tg = TagService.getInstance();
		List all_tag = tg.getAllTag();
		request.setAttribute("all_tag", all_tag);

		request.getRequestDispatcher("/admin/result.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
