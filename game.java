package BrickBrackerGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class game extends JPanel implements KeyListener, ActionListener {
	private boolean play = false;
	private int score = 0;
			
	private int Bricks = 21;
	
	private Timer time;
	private int delay=8;
	private int playerx = 310;
	
	private int positionx=120; 
	private int positiony=350;
	private int directionx=-1;
	private int directiony=-2;
	
	private map mapgenerator;
	
	public game() {
		mapgenerator = new map (3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
	}
	
	public void paint(Graphics g) {
		//game's background
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
		
		//maps
		
		mapgenerator.draw((Graphics2D)g);
		
		//game's border
		g.setColor(Color.green);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(684  ,0,3,592);
		
		//scores
		g.setColor(Color.white);
		g.setFont(new Font("times new roman",Font.BOLD,25));
		g.drawString(""+score, 590, 30);
		
		//the paddle
		g.setColor(Color.yellow);
		g.fillRect(playerx, 550, 100, 8);
		//the ball
		g.setColor(Color.red);
		g.fillOval(positionx ,positiony, 20, 20);
		
		if(Bricks<=0)
		{
			play = false;
			directionx=0;
			directiony=0;
			g.setColor(Color.RED);
			g.setFont(new Font("times new roman",Font.BOLD,30));
			g.drawString("You Won!", 270, 300);
			
			g.setFont(new Font("times new roman",Font.BOLD,20));
			g.drawString("Thank you for playing Bryan's Game",160,370);
			g.drawString("Press Enter to Restart",230,350);
		}
		
		if(positiony>570)
		{
			play = false;
			directionx=0;
			directiony=0;
			g.setColor(Color.RED);
			g.setFont(new Font("times new roman",Font.BOLD,30));
			g.drawString("Game Over, Scores:"+score, 190, 300);
			
			g.setFont(new Font("times new roman",Font.BOLD,20));
			g.drawString("Thank you for playing Bryan's Game",160,370);
			g.drawString("Press Enter to Restart",230,350);
		}
		g.dispose();
	} 
			
 
	@Override
	public void actionPerformed(ActionEvent e) {
		time.restart();
		if(play) {
			if(new Rectangle (positionx,positiony,20,20).intersects(new Rectangle(playerx,550,100,8)))
			{
				directiony = - directiony;
			}
			B:for(int i=0;i<mapgenerator.maps.length;i++)
			{
				for(int j = 0;j<mapgenerator.maps[0].length;j++)
				{
					if(mapgenerator.maps[i][j]>0)
					{
						int brickx= j*mapgenerator.brickw+80;
						int bricky= i*mapgenerator.brickh+50;
						int brickw= mapgenerator.brickw;
						int brickh= mapgenerator.brickh;
						
						Rectangle rect = new Rectangle(brickx,bricky,brickw,brickh);
						Rectangle ballrect =  new Rectangle(positionx,positiony,20,20);
						Rectangle brickrect = rect;
						
						if(ballrect.intersects(brickrect))
						{
							mapgenerator.setBrick(0, i, j);
							Bricks--;
							score+=5;
							
							if(positionx+19<=brickrect.x|| positionx +1>=brickrect.x+brickrect.width)
							{
								directionx = -directionx;
							}
							else
							{
								directiony = -directiony;
							}
							break B;
						}
					}
				}
			}
			positionx += directionx;
			positiony += directiony;
			if(positionx<0)//left
			{
				directionx = -directionx;
			}
			if(positiony<0)//top
			{
				directiony = -directiony;
			}
			if(positionx>670)//right
			{
				directionx = -directionx;
			}
		}
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(playerx >=600)
			{
				playerx = 600;
			} else {
				moveRight();
			}
		
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(playerx <10)
			{
				playerx = 10;
			} 
			else
			{
				moveLeft();
			}
		
		}
		if(e.getKeyCode()== KeyEvent.VK_ENTER)
		{
			if(!play)
			{
				play=true;
				positionx = 120;
				positiony = 350;
				directionx = -1;
				directiony = -2;
				playerx = 310;
				score=0;
				Bricks=21;
				mapgenerator = new map (3,7);
				repaint();
			}
		}
	}
	public void moveRight()
	{
		play = true;
		playerx+=20;
	}
	public void moveLeft()
	{
		play = true;
		playerx-=20; 
	}

}
