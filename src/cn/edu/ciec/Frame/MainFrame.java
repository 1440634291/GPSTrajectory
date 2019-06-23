package cn.edu.ciec.Frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.drew.imaging.ImageProcessingException;

import cn.edu.ciec.photoExit.PhotoExitReader;

public class MainFrame {

	private JFrame frame;

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
				
				//读取照片名称及其路径信息
				if(file.isDirectory()){
					System.out.println("文件夹:"+file.getAbsolutePath());
				}else if(file.isFile()){
					System.out.println("文件:"+file.getAbsolutePath());
				}
				System.out.println(jfc.getSelectedFile().getName());
				
				//读取照片EXIT中GPS坐标信息
				PhotoExitReader exit = new PhotoExitReader();
				try {
					exit.printImageTags(file);
				} catch (ImageProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSubmit.setBounds(229, 347, 113, 27);
		frame.getContentPane().add(btnSubmit);
	}
}
