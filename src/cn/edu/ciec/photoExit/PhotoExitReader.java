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

	/**
	 * ��ȡ��Ƭ�������Ϣ
	 */
	public static void printImageTags(File file) throws ImageProcessingException, Exception {
		Metadata metadata = ImageMetadataReader.readMetadata(file);
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
				} else if (tagName.equals("GPS Latitude")) {
//					System.err.println("γ�� : " + desc);
					 System.err.println("γ��(�ȷ����ʽ) : "+pointToLatlong(desc));
				} else if (tagName.equals("GPS Longitude")) {
//					System.err.println("����: " + desc);
					 System.err.println("����(�ȷ����ʽ): "+pointToLatlong(desc));
				}
			}
		}
	}

	/**
	 * ��γ�ȸ�ʽ ת��Ϊ �ȷ����ʽ ,�����Ҫ�Ļ����Ե��ø÷�������ת��
	 * 
	 * @param point
	 *            �����
	 * @return
	 */
	public static String pointToLatlong(String point) {
		Double du = Double.parseDouble(point.substring(0, point.indexOf("��")).trim());
		Double fen = Double.parseDouble(point.substring(point.indexOf("��") + 1, point.indexOf("'")).trim());
		Double miao = Double.parseDouble(point.substring(point.indexOf("'") + 1, point.indexOf("\"")).trim());
		Double duStr = du + fen / 60 + miao / 60 / 60;
		return duStr.toString();
		//40.29.33,111.47.14
		//40.0��29.0'33.648000000000025",111.0��47.0'14.15760000000887"
	}
}
