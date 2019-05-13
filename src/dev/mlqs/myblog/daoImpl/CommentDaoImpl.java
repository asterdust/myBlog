package dev.mlqs.myblog.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.mlqs.myblog.dao.CommentDao;
import dev.mlqs.myblog.db.C3P0Connection;
import dev.mlqs.myblog.model.Comment;
import dev.mlqs.myblog.utils.DBUtils;
import dev.mlqs.myblog.utils.SecurityUtils;

public class CommentDaoImpl implements CommentDao {

    private Connection conn;

    private CommentDaoImpl() {
        conn = C3P0Connection.getInstance().getConnection();
    }

    private static CommentDao instance;

    public static CommentDao getInstance() {
        if (instance == null) {
            try {
                instance = new CommentDaoImpl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    public boolean deleteComment(int comment_id) {
        String sql = "DELETE FROM t_comment WHERE id=" + comment_id;
        int result = 0;
        PreparedStatement ps = null;
        try {
            article_sub_comemnt(conn, comment_id);
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps);
            } catch (SQLException e1) {}
        }

        return result > 0;
    }


    private void article_sub_comemnt(Connection conn, int comment_id) {
        String sql = "SELECT article_id FROM t_comment WHERE id =" + comment_id;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int article_id = 0;
            if (rs.next()) {
                article_id = rs.getInt("article_id");
            }
            sql = "UPDATE t_article SET COMMENT=COMMENT - 1 WHERE id=" + article_id;
            ps2 = conn.prepareStatement(sql);
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps, rs);
                DBUtils.Close(ps2);
            } catch (SQLException e1) {}
        }
    }


    @Override
    public boolean addComment(Comment comment) {
        Connection conn = C3P0Connection.getInstance().getConnection();
        String sql = "INSERT INTO t_comment VALUES(null,?,?,?,?,?,?,?)";
        int result = 0;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, comment.getArticle_id());
            ps.setString(2, comment.getNickname());
            ps.setString(3, comment.getContent());
            ps.setString(4, comment.getTime());
            ps.setString(5, comment.getEmail());
            ps.setString(6, comment.getWebsite());
            ps.setBoolean(7, comment.isAdmin());
            result = ps.executeUpdate();

            sql = "UPDATE t_article SET COMMENT = COMMENT+1  WHERE id=" + comment.getArticle_id();
            ps2 = conn.prepareStatement(sql);
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps);
                DBUtils.Close(ps2);
            } catch (SQLException e1) {}
        }
        return result > 0;
    }

    @Override
    public List getComment(int article_id) {

        String sql = "SELECT * FROM t_comment WHERE article_id=? ORDER BY TIME";
        List list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, article_id);
            rs = ps.executeQuery();
            Comment cm;
            list = new ArrayList();
            while (rs.next()) {
                cm = new Comment();
                cm.setId(rs.getInt("id"));
                cm.setArticle_id(rs.getInt("article_id"));
                cm.setNickname(rs.getString("nickname"));
                cm.setTime(rs.getString("time"));
                cm.setEmail(rs.getString("email"));
                cm.setEmail_hash(SecurityUtils.md5Hex(cm.getEmail()));
                cm.setWebsite(rs.getString("website"));
                cm.setContent(rs.getString("content"));
                cm.setAdmin(rs.getBoolean("admin"));
                list.add(cm);
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
}