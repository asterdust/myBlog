package dev.mlqs.myblog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dev.mlqs.myblog.dao.ArticleDao;
import dev.mlqs.myblog.daoImpl.ArticleDaoImpl;
import dev.mlqs.myblog.model.Article;
import dev.mlqs.myblog.model.AxisArticle;
import dev.mlqs.myblog.utils.ArticleUtils;
import dev.mlqs.myblog.utils.StringUtils;

public class ArticleService {

    private ArticleDao dao;

    private static ArticleService instance;

    private ArticleService() {
        dao = ArticleDaoImpl.getInstance();
    }


    public static final ArticleService getInstance() {
        if (instance == null) {
            try {
                instance = new ArticleService();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


    public Article getPreviousArticle(String time) {
        return dao.getANearArticle(time, dao.LESS);
    }


    public Article getNextArticle(String time) {
        return dao.getANearArticle(time, dao.MORE);
    }


    public int getCount(String search_key) {
        return dao.getCount(search_key);
    }


    public List getAxisList() {

        List<Article> articles = dao.getAllArticle();

        List<AxisArticle> axis_list = new ArrayList();

        for (Article a : articles) {
            AxisArticle at = ArticleUtils.getAxisArticle(a);
            axis_list.add(at);
        }

        AxisArticle tmp = null;
        List result = new LinkedList();

        if (!axis_list.isEmpty()) {
            tmp = new AxisArticle();
            tmp.setId(0);
            tmp.setYear(axis_list.get(0).getYear());
            result.add(tmp);
            result.add(axis_list.get(0));
        }

        for (int i = 1; i < axis_list.size(); i++) {
            int present_year = axis_list.get(i).getYear();
            int past_year = axis_list.get(i - 1).getYear();

            if (present_year < past_year) {
                tmp = new AxisArticle();
                tmp.setId(0);
                tmp.setYear(present_year);
                result.add(tmp);
            }
            result.add(axis_list.get(i));
        }

        return result;
    }


    public List<Article> getArticle(String column, String value) {
        return dao.getArticleByColumn(column, value);
    }


    public Map getSortAndCount() {
        return dao.getColumAndCount(dao.SEARCH_SORT);
    }


    public List<Article> getArticle() {
        List<Article> list = dao.getAllArticle();
        for (Article a : list) {
            ArticleUtils.cutContent(list);
            ArticleUtils.cutTime(list);
        }
        return list;
    }

    public List<Article> search (String s) {
        List<Article> list = dao.getAllArticle();
        for (int i = 0; i < list.size(); ++i) {
            if (!list.get(i).toString().matches(".*" + s + ".*")) {
                list.remove(i--);
            }
        }
        ArticleUtils.cutContent(list);
        ArticleUtils.cutTime(list);
        return list;
    }


    public Map getSortAndAirticle(String sort_name) {

        Map map = new HashMap();
        List<Article> articleBySort = null;


        if (sort_name.equals("all") || StringUtils.isEmpty(sort_name)) {
            List<String> sorts = dao.getAllSort();

            for (int i = 0; i < sorts.size(); i++) {
                String sort = sorts.get(i);
                articleBySort = dao.getArticleByColumn("sort", sort);
                ArticleUtils.cutTime(articleBySort);
                map.put(sort, articleBySort);
            }
        } else {

            articleBySort = dao.getArticleByColumn("sort", sort_name);
            ArticleUtils.cutTime(articleBySort);
            map.put(sort_name, articleBySort);
        }
        return map;
    }

    public List getVisitRank() {
        List list = dao.getVisitRank();
        if (list.size() > 10) {
            for (int i = 10; i < list.size(); i++) {
                list.remove(i);
            }
        }
        for (int y = 0; y < list.size(); y++) {

            Article a = (Article) list.get(y);
            if (a.getTitle().length() > 20) {
                a.setTitle(StringUtils.cutString(a.getTitle(), 0, 19) + "...");
            }

        }

        return list;

    }

    public List getAllSort() {
        return dao.getAllSort();
    }

    public void addVisit(int article_id) {
        dao.addVisit(article_id);
    }
}