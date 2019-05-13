package dev.mlqs.myblog.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import dev.mlqs.myblog.utils.DBUtils;
import dev.mlqs.myblog.utils.DateUtils;

public class VisitorDB {

    private static final Connection conn = C3P0Connection.getInstance().getConnection();

    public static void visit (HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String localAddr = request.getLocalAddr();
        String remoteHost = request.getRemoteHost();
        String time = DateUtils.getFormatDate(new Date());
        String sql = "insert into t_visitor values(null,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, remoteAddr);
            ps.setString(2, time);
            ps.setString(3, localAddr);
            ps.setString(4, remoteHost);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps);
            } catch (SQLException e) {}
        }
    }

    public static int totalVisit() {
        int result = 0;
        String sql = "select count(id) from t_visitor";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps, rs);
            } catch (SQLException e) {}
        }
        return result;
    }

    public static int totalMember() {
        int result = 0;
        String sql = "SELECT COUNT(DISTINCT(ip)) FROM t_visitor";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.Close(ps, rs);
            } catch (SQLException e) {}
        }
        return result;
    }
}