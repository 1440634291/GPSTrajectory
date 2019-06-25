package cn.edu.ciec.dao;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.AttributedCharacterIterator;

import cn.edu.ciec.util.DBUtil;

public class DrawTrajectory {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private String time;
	private int flag = 0;
	
	
	public void draw(Graphics g) {
		Connection conn = DBUtil.getconnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from tb_photoinfo";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				x2 = rs.getInt(9) % 100 * 20;
				y2 = rs.getInt(10) % 100 * 20;
				System.out.println(x2);
				System.out.println(y2);
				time = rs.getString(11);
				g.drawString(time, x2 + 10, y2);
				if(flag == 0) {
					x1 = x2;
					y1 = y2;
					flag = 1;
				} else {
					g.drawLine(x1, y1, x2, y2);
					System.out.println("ª≠ÕÍ¡À");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
	}
}
