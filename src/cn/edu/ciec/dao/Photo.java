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
	
	
	//��ȡ��Ƭ�������Ϣ
	public void printImageTags(File file) throws ImageProcessingException, Exception {

		Metadata metadata = ImageMetadataReader.readMetadata(file);
		System.out.println("ͼƬ����: " + file.getName());
		name = file.getName();
		System.out.println("�ļ�·��: " + file.getAbsolutePath());
		for (Directory directory : metadata.getDirectories()) {
			for (Tag tag : directory.getTags()) {
				String tagName = tag.getTagName(); // ��ǩ��
				String desc = tag.getDescription(); // ��ǩ��Ϣ
				if (tagName.equals("Image Height")) {
					System.out.println("ͼƬ�߶�: " + desc);
				} else if (tagName.equals("Image Width")) {
					System.out.println("ͼƬ���: " + desc);
				} else if (tagName.equals("Date/Time Original")) {
					System.out.println("����ʱ��: " + desc);
					time = desc;
				} else if (tagName.equals("GPS Latitude")) {
					System.err.println("γ�� : " + desc);
					x_degree = Double.parseDouble(desc.substring(0, desc.indexOf("��")).trim());
					x_minute = Double.parseDouble(desc.substring(desc.indexOf("��") + 1, desc.indexOf("'")).trim());
					x_second = Double.parseDouble(desc.substring(desc.indexOf("'") + 1, desc.indexOf("\"")).trim());
					x = (int) ((x_degree + x_minute / 60 + x_second / 3600) * 100000);
					System.out.println(x);
				} else if (tagName.equals("GPS Longitude")) {
					System.err.println("����: " + desc);
					y_degree = Double.parseDouble(desc.substring(0, desc.indexOf("��")).trim());
					y_minute = Double.parseDouble(desc.substring(desc.indexOf("��") + 1, desc.indexOf("'")).trim());
					y_second = Double.parseDouble(desc.substring(desc.indexOf("'") + 1, desc.indexOf("\"")).trim());
					y = (int) ((y_degree + y_minute / 60 + y_second / 3600) * 100000);
					System.out.println(y);
				}
			}

		}

	}
	
	
	//������Ƭ��Ϣ
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
	
	
	//�ж���Ƭ�Ƿ����
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
