/**
 * @Author dewibrightman
 * @version 1
 * @Date: 25/11/2019
 * @Title: Lab 8 - Threaded Balls
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball 
{
	private int posX, posY, xSpeed, ySpeed;
	private Color color;
	public final int radius = 10;
	private int ballPanelWidth;
	private int ballPanelHeight;
	private Random r = new Random();

	public Ball() 
	{
		ballPanelWidth = BallsFrame.ballPanelWidth;// gets the pane width
		ballPanelHeight = BallsFrame.ballPanelHeight;// gets the pane height
		posX = r.nextInt(ballPanelWidth - 2 * radius); // random position
		posY = r.nextInt(ballPanelHeight - 2 * radius); // random position
		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)); 
		xSpeed = r.nextInt(30);// random xSpeed
		ySpeed = r.nextInt(30); // random ySpeed

	}

	/**
	 * @return a dimension with the x and y position
	 */
	public Dimension getCenter() 
	{
		int centerX = posX + radius;
		int centerY = posY + radius;
		return (new Dimension(centerX, centerY));
	}

	/**
	 * gets the balls radius
	 * 
	 * @return the radius
	 */
	public int getRadius() 
	{
		return radius;
	}

	/**
	 * draws the ball using
	 * 
	 * @param g2 Graphics2D
	 */
	public void draw(Graphics2D g2)
	{
		Ellipse2D myBall = new Ellipse2D.Double(posX, posY, radius * 2, radius * 2);// double the radius (parameters are
																					// width and height)
		g2.setColor(color);// sets the colour
		g2.fill(myBall);// draws the ball
	}

	/**
	 * The methods updates the position of the ball by calculating the xSpeed and
	 * the ySpeed
	 */
	public void move()
	{
		if (posX <= 0 || (posX + (2 * radius)) >= ballPanelWidth)
			xSpeed *= -1;
		if (posY <= 0 || (posY + (2 * radius)) >= ballPanelHeight)
			ySpeed *= -1;
		posX = posX + xSpeed;
		posY = posY + ySpeed;
	}
}