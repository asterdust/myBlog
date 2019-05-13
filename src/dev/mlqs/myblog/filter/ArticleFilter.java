package dev.mlqs.myblog.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mlqs.myblog.service.ArticleService;


public class ArticleFilter implements Filter {

    public ArticleFilter() {}

    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest rq = (HttpServletRequest) request;
        String id = rq.getParameter("id");
        ArticleService as = ArticleService.getInstance();
        as.addVisit(Integer.valueOf(id));
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) {}
}
