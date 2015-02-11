package com.attilax.seo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import com.attilax.Closure2;
import com.attilax.io.pathx;
import com.attilax.skin.obf.SkinObf;
import com.attilax.win.MenuX;

import javax.swing.JButton;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class jfrmWin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jfrmWin frame = new jfrmWin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public jfrmWin() {
		this.setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 510);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
	//	contentPane.setBorder(new EmptyBorder(0, 0, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	// contentPane.setBackground(Color.gray);
		
		JLabel lblNewLabel = new JLabel("New label");
		  Icon img1=new ImageIcon(pathx.classPath(SkinObf.class)+"/titleH27.png");
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(jfrmWin.class.getResource("/com/attilax/skin/obf/mint.png")));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setRolloverIcon(new ImageIcon(jfrmWin.class.getResource("/com/attilax/skin/obf/minOver.png")));
		btnNewButton.setSelectedIcon(new ImageIcon(jfrmWin.class.getResource("/com/attilax/skin/obf/minOver.png")));
		
		btnNewButton.setBounds(560, 0, 57, 44);
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		contentPane.add(btnNewButton);
	//	lblNewLabel.setb

		
		JButton Max_button = new JButton("");
		Max_button.setMargin(new Insets(0, 0, 0, 0));
		Max_button.setFocusPainted(false);
		Max_button.setContentAreaFilled(false);
		Max_button.setBorderPainted(false);
		Max_button.setBounds(610, 0, 57, 44);
		Max_button.setIcon(new ImageIcon(jfrmWin.class.getResource("/com/attilax/skin/obf/max.png")));
		Max_button.setRolloverIcon(new ImageIcon(jfrmWin.class.getResource("/com/attilax/skin/obf/minOver.png")));
		contentPane.add(Max_button);
		
		JButton exit_button = new JButton("");
		exit_button.setMargin(new Insets(0, 0, 0, 0));
		exit_button.setFocusPainted(false);
		exit_button.setContentAreaFilled(false);
		exit_button.setBorderPainted(false);
		exit_button.setBounds(673, 0, 42, 44);
		exit_button.setIcon(new ImageIcon(jfrmWin.class.getResource("/com/attilax/skin/obf/exit.png")));
		exit_button.setRolloverIcon(new ImageIcon(jfrmWin.class.getResource("/com/attilax/skin/obf/minOver.png")));
		contentPane.add(exit_button);
		
		JButton start_btn = new JButton("");
		start_btn.setMargin(new Insets(0, 0, 0, 0));
		start_btn.setFocusPainted(false);
		start_btn.setContentAreaFilled(false);
		start_btn.setBorderPainted(false);
		start_btn.setBounds(138, 107, 114, 99);
		start_btn.setIcon(new ImageIcon(jfrmWin.class.getResource("/com/attilax/skin/obf/start.png")));
		start_btn.setRolloverIcon(new ImageIcon(jfrmWin.class.getResource("/com/attilax/skin/obf/minOver.png")));
		contentPane.add(start_btn);
		
		
		JButton stop_btn = new JButton("");
		stop_btn.setMargin(new Insets(0, 0, 0, 0));
		stop_btn.setFocusPainted(false);
		stop_btn.setContentAreaFilled(false);
		stop_btn.setBorderPainted(false);
		stop_btn.setBounds(420, 110, 102, 88);
		stop_btn.setIcon(new ImageIcon(jfrmWin.class.getResource("/com/attilax/skin/obf/stop.png")));
		stop_btn.setRolloverIcon(new ImageIcon(jfrmWin.class.getResource("/com/attilax/skin/obf/minOver.png")));
		contentPane.add(stop_btn);
		
		
		
		lblNewLabel.setIcon(img1);
		lblNewLabel.setBounds(0, 0, 752, 33);
		contentPane.add(lblNewLabel);
		
		//set bg pic 
		  ((JPanel) this.getContentPane()).setOpaque(false);
		  ImageIcon img = new ImageIcon(jfrmWin.class.getResource("/com/attilax/skin/obf/bg.jpg")); // 添加图片
	        JLabel lblPicture = new JLabel(img);
	        this.getLayeredPane().add(lblPicture, new Integer(Integer.MIN_VALUE));
	        lblPicture.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	        
	        //set menu
	        setMenu();
	}

	private void setMenu() {
		
		JLabel lblNewLabel_1 = new JLabel("URL验证码测试地址");
		lblNewLabel_1.setBounds(20, 70, 108, 15);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(138, 64, 561, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		JMenuBar menuBar   = new JMenuBar();
		menuBar.setBounds(0,30,1000,30);
		
		this.getContentPane().add(menuBar);
		MenuX mx=new MenuX();
		mx.menubar=menuBar;
		
		JLabel lblStart = new JLabel("start开始");
		lblStart.setBounds(252, 167, 54, 15);
		contentPane.add(lblStart);
		
		JLabel lblStop = new JLabel("stop停止");
		lblStop.setBounds(532, 167, 54, 15);
		contentPane.add(lblStop);
		
		JLabel lblResult = new JLabel("Result结果");
		lblResult.setBounds(58, 288, 90, 15);
		contentPane.add(lblResult);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(138, 288, 509, 165);
		contentPane.add(textArea);
		
		JLabel lblProcess = new JLabel("Process...处理过程：：：");
		lblProcess.setBounds(58, 236, 149, 15);
		contentPane.add(lblProcess);
		try {
			mx.geneMenu(new Closure2<String,Object> (){

				@Override
				public Object execute(String arg0) {
					// TODO Auto-generated method stub
					return null;
				}});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
