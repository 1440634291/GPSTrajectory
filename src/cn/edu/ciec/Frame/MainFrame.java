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

import cn.edu.ciec.dao.Photo;

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
		label.setFont(new Font("�����п�", Font.PLAIN, 40));
		label.setBounds(210, 13, 160, 37);
		frame.getContentPane().add(label);
		
		JButton btnSubmit = new JButton("ѡ��ͼƬ");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );
				jfc.showDialog(new JLabel(), "ѡ��");
				File file = jfc.getSelectedFile();
				
				
				//��ȡ��Ƭ��Ϣ
				Photo photo = new Photo();
				try {
					photo.printImageTags(file);
					if(!photo.IsExistPhoto())
						photo.photoSave();
					else
						System.out.println("�Ѵ��ڸ���Ƭ");
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
