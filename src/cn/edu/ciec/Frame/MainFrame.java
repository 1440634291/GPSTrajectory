package cn.edu.ciec.Frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("\u8BF7\u9009\u62E9\u4F60\u7684\u7167\u7247");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("ËÎÌו", Font.PLAIN, 18));
		frame.getContentPane().add(label, BorderLayout.NORTH);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(button, BorderLayout.SOUTH);
	}

}
