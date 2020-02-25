package BrickBrackerGame;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
	JFrame  frame = new JFrame();
	game x = new game();
	frame.setBounds(10,10,700,600);
	frame.setTitle("BrickBracker Game");
	frame.setResizable(false);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(x);
	

	} 

}
   