package dev.mlqs.myblog.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class C3P0Connection {

	private static C3P0Connection instance;

	private static ComboPooledDataSource dataSource;

	private C3P0Connection() {
		dataSource = new ComboPooledDataSource();
	}

	
	public static final C3P0Connection getInstance() {
		if (instance == null) {
			try {
				instance = new C3P0Connection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	
	public synchronized final Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
