package cn.edu.ciec.Frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.drew.imaging.ImageProcessingException;

import cn.edu.ciec.dao.DrawTrajectory;
import cn.edu.ciec.dao.Photo;

public class MainFrame {

	private JFrame frame;
	JOptionPane j = new JOptionPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 589, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528");
		label.setForeground(Color.ORANGE);
		label.setFont(new Font("华文行楷", Font.PLAIN, 40));
		label.setBounds(210, 13, 160, 37);
		frame.getContentPane().add(label);
		
		JButton btnSubmit = new JButton("选择图片");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );
				jfc.showDialog(new JLabel(), "选择");
				File file = jfc.getSelectedFile();
				
				
				//读取照片信息并保存
				Photo photo = new Photo();
				try {
					if(file != null) {
						photo.printImageTags(file);
						if(!photo.IsExistPhoto())
							photo.photoSave();
						else
							j.showMessageDialog(null, "该照片已添加，请重新选择", "错误信息", JOptionPane.ERROR_MESSAGE);
							System.out.println("已存在该照片");
					}else {
						j.showMessageDialog(null, "所选照片不能为空", "错误信息", JOptionPane.ERROR_MESSAGE);
						System.out.println("所选照片不能为空");
					}
					
				} catch (ImageProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSubmit.setBounds(88, 348, 113, 27);
		frame.getContentPane().add(btnSubmit);
		
		JButton button = new JButton("\u5F00\u59CB\u7ED8\u56FE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Frame newFrame = new Frame();
//				newFrame.setVisible(true);
//				newFrame.setSize(100, 200);
				RouteFrame newFrame = new RouteFrame();
				newFrame.setVisible(true);
				newFrame.setSize(1920, 1080);
				System.out.println("开始绘图");
			}});
		
		button.setBounds(372, 348, 113, 27);
		frame.getContentPane().add(button);
	}
}
