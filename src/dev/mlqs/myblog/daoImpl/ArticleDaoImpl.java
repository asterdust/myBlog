package dev.mlqs.myblog.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.mlqs.myblog.dao.ArticleDao;
import dev.mlqs.myblog.db.C3P0Connection;
import dev.mlqs.myblog.model.Article;
import dev.mlqs.myblog.utils.DBUtils;

public class ArticleDaoImpl implements ArticleDao {
    private Connection conn;

    private static ArticleDao instance;

    private ArticleDaoImpl() {
        conn = C3P0Connection.getInstance().getConnection();
    }

    public static final ArticleDao getInstance() {
        if (instance == null) {
            try {
                instance = new ArticleDaoImpl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    public void addVisit(int article_id) {

        String sql = "update t_article set visit = visit+1 where id = " + article_id;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps);
            } catch (SQLException e1) {}
        }
    }

    @Override
    public Article getANearArticle(String time, int less_or_more) {

        Article article = null;
        String sql = null;
        if (less_or_more == this.LESS) {
            sql = " SELECT  * FROM t_article WHERE TIME< '" + time + "'  ORDER BY TIME DESC ";
        } else if (less_or_more == this.MORE) {
            sql = " SELECT  * FROM t_article WHERE TIME > '" + time + "'  ORDER BY TIME ";
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                article = new Article(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("sort"),
                        rs.getString("time"),
                        rs.getInt("comment"),
                        rs.getInt("visit"),
                        rs.getString("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps, rs);
            } catch (SQLException e1) {}
        }
        return article;
    }

    @Override
    public Map getColumAndCount(String search_column) {

        String sql = " select " + search_column + " ,count(" + search_column + ") as counts  from t_article  group by "
                + search_column;
        Map map = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            map = new HashMap();
            while (rs.next()) {
                map.put(rs.getString(search_column), rs.getInt("counts"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps, rs);
            } catch (SQLException e1) {}
        }
        return map;
    }

    @Override
    public List getAllSort() {

        String sql = "select distinct(sort) from t_article order by sort";
        List list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            list = new ArrayList();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps, rs);
            } catch (SQLException e1) {}
        }
        return list;
    }

    @Override
    public Article addArticle(Article a) {
        String sql = "insert into t_article values(null,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getAuthor());
            ps.setString(3, a.getSort());
            ps.setString(4, a.getTime());
            ps.setInt(5, a.getComment());
            ps.setInt(6, a.getVisit());
            ps.setString(7, a.getContent());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps);
            } catch (SQLException e1) {}
        }
        return this.getLastArticle();
    }

    @Override
    public Article updateArticle(Article a) {
        String sql = "update t_article set title=?,author=?,sort=?,time=?,comment=?,visit=?,content=? where id=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getAuthor());
            ps.setString(3, a.getSort());
            ps.setString(4, a.getTime());
            ps.setInt(5, a.getComment());
            ps.setInt(6, a.getVisit());
            ps.setString(7, a.getContent());
            ps.setInt(8, a.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                DBUtils.Close(ps);
            } catch (SQLException e1) {}
        }
        return a;
    }

    private Article getLastArticle() {
        String sql = "SELECT * FROM t_article ORDER BY TIME DESC LIMIT 1";
        Article article = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                article = new Article(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("sort"),
                        rs.getString("time"),
                        rs.getInt("comment"),
                        rs.getInt("visit"),
                        rs.getString("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps, rs);
            } catch (SQLException e1) {}
        }
        return article;
    }

    @Override
    public boolean deleteArticle(String id) {

        String sql = "delete from t_article where id=?";
        PreparedStatement ps = null;
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            if (ps != null) try {
                    DBUtils.Close(ps);
                } catch (SQLException e1) {}
        } finally {
            try {
                DBUtils.Close(ps);
            } catch (SQLException e1) {}
        }
        return result != 0;
    }

    @Override
    public List getAllArticle() {
        List<Article> list = new ArrayList();

        String sql = "select * from t_article";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Article article = new Article(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("sort"),
                        rs.getString("time"),
                        rs.getInt("comment"),
                        rs.getInt("visit"),
                        rs.getString("content"));
                list.add(article);
            }
            Collections.sort(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps, rs);
            } catch (SQLException e1) {}
        }
        return list;
    }

    @Override
    public List getVisitRank() {
        List<Article> list = new ArrayList();

        String sql = "SELECT * FROM t_article ORDER BY visit DESC";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Article article = new Article(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("sort"),
                        rs.getString("time"),
                        rs.getInt("comment"),
                        rs.getInt("visit"),
                        rs.getString("content"));
                list.add(article);
            }
            DBUtils.Close(ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps, rs);
            } catch (SQLException e1) {}
        }
        return list;
    }

    @Override
    public List<Article> getArticleByColumn(String column, String value) {
        List<Article> list = null;
        Article at = null;
        String sql = "select * from t_article where " + column + " = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            list = new ArrayList();
            while (rs.next()) {
                at = new Article(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("sort"),
                        rs.getString("time"), rs.getInt("comment"), rs.getInt("visit"),
                        rs.getString("content"));
                list.add(at);
            }
            DBUtils.Close(ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps, rs);
            } catch (SQLException e1) {}
        }
        return list;
    }

    @Override
    public int getCount(String search_key) {
        String sql;
        if (search_key.equals(SEARCH_ARTICLE)) {
            sql = "SELECT COUNT(id) FROM t_article";
        } else {
            sql = "SELECT COUNT(DISTINCT(sort)) FROM t_article";
        }
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
             ps = conn.prepareStatement(sql);
             rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            DBUtils.Close(ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps, rs);
            } catch (SQLException e1) {}
        }
        return result;
    }

    @Override
    public boolean updateSort(String old_sort, String new_sort) {
        String sql = "UPDATE t_article SET sort=? WHERE sort=?";
        int result = 0;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, new_sort);
            ps.setString(2, old_sort);
            ps.executeUpdate();
            DBUtils.Close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps);
            } catch (SQLException e1) {}
        }
        return result > 0;
    }

    @Override
    public boolean deleteSort(String sort) {
        String sql = "SELECT * FROM t_article where sort = ?";
        int result = 0;
        List<Article> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sort);
            rs = ps.executeQuery();

            while (rs.next()) {
                Article article = new Article(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("sort"),
                        rs.getString("time"),
                        rs.getInt("comment"),
                        rs.getInt("visit"),
                        rs.getString("content"));
                list.add(article);
            }

            for (Article article : list) {
                article.setSort("无分类");
                updateArticle(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps, rs);
            } catch (SQLException e1) {}
        }
        return result > 0;
    }
}
