package com.wondersgroup.utils.Listener;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

	public class keyBoardListener extends JFrame{
		
		keyBoard  kb = null;
		public static void main(String[] args) {
			
			keyBoardListener listener = new keyBoardListener();
		}
			
		public keyBoardListener(){
			kb = new keyBoard();
			this.add(kb);
	        this.addKeyListener(kb);
	        this.setSize(400,300);
	        this.setLocation(300,280);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setVisible(true); 
		}
		
	}
	
	class keyBoard extends JPanel implements KeyListener{
	
		 public void paint(Graphics g){
		        super.paint(g);
		    }    
		    public void keyTyped(KeyEvent e){ 
		    	// 有字符输出的函数
		    }
		    public void keyPressed(KeyEvent e) {
		        System.out.println((char)e.getKeyCode()+" 键被按下"); //这里的char是显示按下键对应字母，否则显示的数字
		    }
		    public void keyReleased(KeyEvent e) {
		       //按键抬起
		    }  
	}