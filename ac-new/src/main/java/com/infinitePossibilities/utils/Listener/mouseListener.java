package com.infinitePossibilities.utils.Listener;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mouseListener extends JFrame{
	
			mouse mouse = null;
			
			public static void main(String[] args) {
				mouseListener listener = new mouseListener();
			}
			
			public mouseListener() {
				mouse = new mouse();
				this.add(mouse);
				this.addKeyListener(mouse);
				this.addMouseListener(mouse);
		        this.addMouseMotionListener(mouse);
		        this.setSize(700,800);
		        this.setLocation(500,600);
		        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        this.setVisible(true);
			}
	
}

class mouse extends JPanel implements MouseListener,KeyListener,MouseMotionListener{ //这里要用逗号 否则报错

			public void paint(Graphics g) {
		        super.paint(g);
		    }
		    public void keyPressed(KeyEvent e) {
		        System.out.println(e.getKeyChar()+"键被按下");
		    }
		    public void keyTyped(KeyEvent e){
		    }
		    public void keyReleased(KeyEvent e){
		    }
		    public void mouseClicked(MouseEvent e) {
		        System.out.println(e.getX()+"   "+e.getY());
		    }
		    public void mousePressed(MouseEvent e) {
		        System.out.println("鼠标被按下");
		    }
		    public void mouseReleased(MouseEvent e) {
		        System.out.println("鼠标松开");
		    }
		    public void mouseEntered(MouseEvent e) {
		        System.out.println("鼠标进来了");
		    }
		    public void mouseExited(MouseEvent e) {
		        System.out.println("鼠标出去了");
		    }
		    public void mouseDragged(MouseEvent e){//按住拖
		        System.out.println(e.getX()+"   "+e.getY());
		    }
		    public void mouseMoved(MouseEvent e) {
		        System.out.println("动了动了");
		    }
}