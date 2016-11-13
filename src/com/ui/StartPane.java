package com.ui;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.resource.Img;

/**
 * 游戏开始面板
 * 
 * @author 林创斌
 *
 */
public class StartPane extends JPanel {

	private static final long serialVersionUID = 1L;
	// 开始按钮
	private JButton startBtn;
	// 说明按钮
	private JButton aboutBtn;
	// 宽度
	private int width;
	// 高度
	private int height;

	public StartPane(int width, int height) {
		this.height = height;
		this.width = width;
		this.setBounds(0, 0, width, height);
		initComponent();
		this.setLayout(null);
		this.setSize(width, height);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(Img.startImage, 0, 0, width, height, null);
	}

	/**
	 * 初始化组件
	 */
	public void initComponent() {
		startBtn = new JButton(Img.startBtnImage.get(0));
		startBtn.setRolloverIcon(Img.startBtnImage.get(1));
		startBtn.setPressedIcon(Img.startBtnImage.get(2));
		startBtn.setContentAreaFilled(false);
		startBtn.setBorder(null);
		aboutBtn = new JButton(Img.aboutBtnImage.get(0));
		aboutBtn.setRolloverIcon(Img.aboutBtnImage.get(1));
		aboutBtn.setPressedIcon(Img.aboutBtnImage.get(2));
		aboutBtn.setContentAreaFilled(false);
		aboutBtn.setBorder(null);
		startBtn.setBounds((width - 160) / 2, (height) / 2 + 100, 160, 46);
		aboutBtn.setBounds((width - 160) / 2, (height) / 2 + 160, 160, 46);
		this.add(startBtn);
		this.add(aboutBtn);
	}

	public JButton getStartBtn() {
		return startBtn;
	}

	public JButton getAboutBtn() {
		return aboutBtn;
	}

}
