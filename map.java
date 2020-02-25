package BrickBrackerGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class map {
	public int maps[][];
	public int brickw;
	public int brickh;
	public map(int row, int col) {
		maps = new int[row][col];
		for(int i= 0;i<maps.length;i++)
		{
			for(int j=0;j<maps[0].length;j++) 
			{
				maps[i][j] = 1;
			}
		}
		brickw=540/col;
		brickh=150/row; 
	}
public void draw(Graphics2D g)
{
	for(int i= 0;i<maps.length;i++)
	{
		for(int j=0;j<maps[0].length;j++) 
		{
			if(maps[i][j] > 0)
				{
				 g.setColor(Color.white );
				 g.fillRect(j*brickw+80,i*brickh+50,brickw,brickh);
				 
				 g.setStroke(new BasicStroke(3));
				 g.setColor(Color.black);
				 g.drawRect(j*brickw+80,i*brickh+50,brickw,brickh);
				}
		}
}
}
public void setBrick(int value, int row, int col)
{
	maps[row][col] = value;
}
}
