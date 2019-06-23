package cn.edu.ciec.dataprocessing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.edu.ciec.util.DBUtil;

public class PhotoProcessing {
	private String name;
	private int id;
	private double x_degree;
	private double x_minute;
	private double x_second;
	private double y_degree;
	private double y_minute;
	private double y_second;
	
	
	public PhotoProcessing(String name, double x_degree, double x_minute, double x_second, double y_degree,
			double y_minute, double y_second) {
		super();
		this.name = name;
		this.x_degree = x_degree;
		this.x_minute = x_minute;
		this.x_second = x_second;
		this.y_degree = y_degree;
		this.y_minute = y_minute;
		this.y_second = y_second;
	}


	public PhotoProcessing() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void photoSave(String name, double x_degree, double x_minute, 
			double x_second, double y_degree, double y_minute, 
			double y_second) {
		Connection conn = DBUtil.getconnection();
		PreparedStatement pstmt = null;
		String sql = "Insert into tb_myphotodb(photo_name,photo_gps_x_degree,photo_gps_x_minute,"
				+ "photo_gps_x_second,photo_gps_y_degree,photo_gps_y_minute,photo_gps_y_second)"
				+ " values(?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setDouble(2, x_degree);
			pstmt.setDouble(3, x_minute);
			pstmt.setDouble(4, x_second);
			pstmt.setDouble(5, y_degree);
			pstmt.setDouble(6, y_minute);
			pstmt.setDouble(7, y_second);
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		
	}
	
	
}
