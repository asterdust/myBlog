package dev.mlqs.myblog.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.mlqs.myblog.dao.TagDao;
import dev.mlqs.myblog.db.C3P0Connection;
import dev.mlqs.myblog.model.Tag;
import dev.mlqs.myblog.utils.DBUtils;


public class TagDaoImpl implements TagDao {

    private Connection conn;

    private static TagDao instance;

    private TagDaoImpl() {
        conn = C3P0Connection.getInstance().getConnection();
    }

    public static final TagDao getInstance() {
        if (instance == null) {
            try {
                instance = new TagDaoImpl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


    @Override
    public boolean addTag(int id, String tag) {
        String sql = "insert into t_tag values(?,?)";
        int result = 0;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, tag);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps);
            } catch (SQLException e1) {}
        }
        return result != 0;
    }


    @Override
    public boolean deleteTag(int id, String tag) {

        String sql = "delete from t_tag where id=? or tag=?";
        int result = 0;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, tag);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps);
            } catch (SQLException e1) {}
        }
        return result != 0;
    }


    @Override
    public List getAllTag() {
        String sql = "SELECT distinct(tag) FROM t_tag";
        List<Tag> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            Tag tag;
            while (rs.next()) {
                tag = new Tag();
                tag.setTag(rs.getString(1));
                tag.setId(0);
                list.add(tag);
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
    public boolean updateTag(String old_tag, String new_tag) {
        String sql = "update t_tag set tag=? where tag=?";
        int result = 0;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, new_tag);
            ps.setString(2, old_tag);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps);
            } catch (SQLException e1) {}
        }
        return result != 0;
    }


    @Override
    public List getTagByColumn(String column, String value) {

        String sql = "select * from t_tag where " + column + "=?";
        List<Tag> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            Tag tag;
            while (rs.next()) {
                tag = new Tag();
                tag.setId(rs.getInt("id"));
                tag.setTag(rs.getString("tag"));
                list.add(tag);
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
