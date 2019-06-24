package cn.edu.ciec.photoExit;

import java.io.File;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;


public class PhotoExitReader {
//	public static void main(String[] args) throws Exception, Exception {
//		File file = new File("F:\\1234.jpg");
//		printImageTags(file);
//	}
	private static String name;
	private static double x_degree;
	private static double x_minute;
	private static double x_second;
	private static double y_degree;
	private static double y_minute;
	private static double y_second;
	/**
	 * 读取照片里面的信息
	 */
	public static void printImageTags(File file) throws ImageProcessingException, Exception {
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
				} else if (tagName.equals("GPS Latitude")) {
					System.err.println("纬度 : " + desc);
					x_degree = Double.parseDouble(desc.substring(0, desc.indexOf("°")).trim());
					x_minute = Double.parseDouble(desc.substring(desc.indexOf("°") + 1, desc.indexOf("'")).trim());
					x_second = Double.parseDouble(desc.substring(desc.indexOf("'") + 1, desc.indexOf("\"")).trim());
//					 System.err.println("纬度(度分秒格式) : "+pointToLatlong(desc));
				} else if (tagName.equals("GPS Longitude")) {
					System.err.println("经度: " + desc);
					y_degree = Double.parseDouble(desc.substring(0, desc.indexOf("°")).trim());
					y_minute = Double.parseDouble(desc.substring(desc.indexOf("°") + 1, desc.indexOf("'")).trim());
					y_second = Double.parseDouble(desc.substring(desc.indexOf("'") + 1, desc.indexOf("\"")).trim());
//					 System.err.println("经度(度分秒格式): "+pointToLatlong(desc));
				}
			}
		}
	}

	/**
	 * 经纬度格式 转换为 度分秒格式 ,如果需要的话可以调用该方法进行转换
	 * 
	 * @param point
	 *            坐标点
	 * @return
	 */
	public static String pointToLatlong(String point) {
		Double du = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());
		Double fen = Double.parseDouble(point.substring(point.indexOf("°") + 1, point.indexOf("'")).trim());
		Double miao = Double.parseDouble(point.substring(point.indexOf("'") + 1, point.indexOf("\"")).trim());
		Double duStr = du + fen / 60 + miao / 60 / 60;
		return duStr.toString();
		//40.29.33,111.47.14
		//40.0°29.0'33.648000000000025",111.0°47.0'14.15760000000887"
		//40.49268 111.787266
		//40.492688 111.787256
	}
}
