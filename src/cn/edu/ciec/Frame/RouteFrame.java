package cn.edu.ciec.Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.ciec.dao.DrawTrajectory;

import java.awt.Color;

public class RouteFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RouteFrame frame = new RouteFrame();
					frame.setVisible(true);
//					DrawTrajectory draw = new DrawTrajectory();
//					draw.draw(Graphics g);
//					paint
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RouteFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public void paint(Graphics g) {
		DrawTrajectory draw = new DrawTrajectory();
		draw.draw(g);
//		g.drawLine(100, 100, 100, 400);
	}

}
