package dev.mlqs.myblog.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dev.mlqs.myblog.dao.ArticleDao;
import dev.mlqs.myblog.dao.TagDao;
import dev.mlqs.myblog.daoImpl.ArticleDaoImpl;
import dev.mlqs.myblog.daoImpl.TagDaoImpl;
import dev.mlqs.myblog.model.Article;
import dev.mlqs.myblog.utils.FailException;
import dev.mlqs.myblog.utils.Form2Bean;

public class AdminService {

    private ArticleDao adao;
    private TagDao tdao;
    private static AdminService instance;

    private AdminService() {
        adao = ArticleDaoImpl.getInstance();
        tdao = TagDaoImpl.getInstance();
    }

    
    public static final AdminService getInstance() {
        if (instance == null) {
            try {
                instance = new AdminService();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Article addArticle(HttpServletRequest request) {
        
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        Article article = null;
        try {
            article = Form2Bean.articleForm2Bean(request);
        } catch (FailException e) {
            e.printStackTrace();
        }
        if (article == null)
            return null;
        Article a = adao.addArticle(article);
        if (a == null)
            return null;
        
        String str = request.getParameter("tags").trim();
        String[] tags = str.split(" ");
        for (String tag : tags) {
            tdao.addTag(a.getId(), tag);
        }
        return a;
    }

    public Article updateArticle(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        Article article = null;
        try {
            article = Form2Bean.articleForm2Bean(request);
        } catch (FailException e) {
            e.printStackTrace();
        }
        if (article == null)
            return null;
        Article a = adao.updateArticle(article);
        if (a == null)
            return null;
        
        String str = request.getParameter("tags").trim();
        String[] tags = str.split(" ");
        for (String tag : tags) {
            tdao.addTag(a.getId(), tag);
        }


        String old_id = request.getParameter("id");


        this.delteArticle(old_id);

        return this.addArticle(request);
    }

    public Article getArticle(String article_id) {
        List<Article> list = adao.getArticleByColumn("id", article_id);
        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    public boolean delteArticle(String id) {
        return adao.deleteArticle(id);
    }

    public boolean updateSort(String old_sort, String new_sort) {
        return adao.updateSort(old_sort, new_sort);
    }

    public boolean deleteSort(String sort) {
        return adao.deleteSort(sort);
    }

    public boolean updateTag(String old_tag, String new_tag) {
        boolean result = tdao.updateTag(old_tag, new_tag);
        return result;
    }

    public boolean deleteTag(String tag) {
        
        boolean result = tdao.deleteTag(TagDao.DEFAULT_ID, tag);
        return result;
    }
}
