package cn.edu.ciec.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import cn.edu.ciec.util.DBUtil;

public class Photo {
	private String name;
	private int id;
	private double x_degree;
	private double x_minute;
	private double x_second;
	private double y_degree;
	private double y_minute;
	private double y_second;
	private int x;
	private int y;
	private String time;
	
	
	public Photo(String name, double x_degree, double x_minute, double x_second, double y_degree, double y_minute,
			double y_second) {
		super();
		this.name = name;
		this.x_degree = x_degree;
		this.x_minute = x_minute;
		this.x_second = x_second;
		this.y_degree = y_degree;
		this.y_minute = y_minute;
		this.y_second = y_second;
	}


	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//读取照片里面的信息
	public void printImageTags(File file) throws ImageProcessingException, Exception {

		Metadata metadata = ImageMetadataReader.readMetadata(file);
		System.out.println("图片名称: " + file.getName());
		name = file.getName();
		System.out.println("文件路径: " + file.getAbsolutePath());
		for (Directory directory : metadata.getDirectories()) {
			for (Tag tag : directory.getTags()) {
				String tagName = tag.getTagName(); // 标签名
				String desc = tag.getDescription(); // 标签信息
				if (tagName.equals("Image Height")) {
					System.out.println("图片高度: " + desc);
				} else if (tagName.equals("Image Width")) {
					System.out.println("图片宽度: " + desc);
				} else if (tagName.equals("Date/Time Original")) {
					System.out.println("拍摄时间: " + desc);
					time = desc;
				} else if (tagName.equals("GPS Latitude")) {
					System.err.println("纬度 : " + desc);
					x_degree = Double.parseDouble(desc.substring(0, desc.indexOf("°")).trim());
					x_minute = Double.parseDouble(desc.substring(desc.indexOf("°") + 1, desc.indexOf("'")).trim());
					x_second = Double.parseDouble(desc.substring(desc.indexOf("'") + 1, desc.indexOf("\"")).trim());
					x = (int) ((x_degree + x_minute / 60 + x_second / 3600) * 100000);
					System.out.println(x);
				} else if (tagName.equals("GPS Longitude")) {
					System.err.println("经度: " + desc);
					y_degree = Double.parseDouble(desc.substring(0, desc.indexOf("°")).trim());
					y_minute = Double.parseDouble(desc.substring(desc.indexOf("°") + 1, desc.indexOf("'")).trim());
					y_second = Double.parseDouble(desc.substring(desc.indexOf("'") + 1, desc.indexOf("\"")).trim());
					y = (int) ((y_degree + y_minute / 60 + y_second / 3600) * 100000);
					System.out.println(y);
				}
			}

		}

	}
	
	
	//保存照片信息
	public void photoSave() {
		Connection conn = DBUtil.getconnection();
		PreparedStatement pstmt = null;
		String sql = "insert into tb_photoinfo(photo_name,x_degree,"
				+ "x_minute,x_second,y_degree,y_minute,y_second,"
				+ "x,y,photo_time)"
				+ " values(?,?,?,?,?,?,?,?,?,?)";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setDouble(2, x_degree);
			pstmt.setDouble(3, x_minute);
			pstmt.setDouble(4, x_second);
			pstmt.setDouble(5, y_degree);
			pstmt.setDouble(6, y_minute);
			pstmt.setDouble(7, y_second);
			pstmt.setInt(8, x);
			pstmt.setInt(9, y);
			pstmt.setString(10, time);
			System.out.println(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		
	}
	
	
	//判断照片是否存在
	public boolean IsExistPhoto() {
		boolean flag = false;
		Connection conn = DBUtil.getconnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from tb_photoinfo where photo_name = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				flag = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		
		
		return flag;
	}
	
	
}
