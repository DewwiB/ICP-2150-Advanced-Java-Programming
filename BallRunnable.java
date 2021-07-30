/**
 * @Author dewibrightman
 * @version 1
 * @Date: 25/11/2019
 * @Title: Lab 8 - Threaded Balls
 */

import javax.swing.JPanel;

public class BallRunnable implements Runnable {
	private static boolean moveAllBalls = true;
	private static int UpdateRate = 60; // default frame rate
	private static int UpdateVariance = 30; // amount of incremental frame
	Ball thisBall;
	JPanel myPanel;

	public BallRunnable(Ball ball, BallPanel BallPanel) 
	{
		super();
		thisBall = ball;
		myPanel = BallPanel;
	}

	/**
	 * sets the static variable of the update rate (frames per second)
	 * 
	 * @param mult which indicates how fast or slow the frame rate should be
	 */
	public static void setUpdateRate(int mult) 
	{
		UpdateRate = mult * UpdateVariance;
	}

	/**
	 * sets the static variable moveBball
	 * 
	 * @param bool
	 */
	public static void setMoveBall(Boolean bool) 
	{
		moveAllBalls = bool;
	}

	/**
	 * gets the static variable moveAllBalls
	 * 
	 * @return moveAllBalls
	 */
	public static boolean getMoveBall() 
	{
		return moveAllBalls;
	}

	/**
	 * runs the runnable object
	 */
	@Override
	public void run() 
	{
		// System.out.println("in run");
		while (!Thread.interrupted()) // while thread is not interrupted
		{
			while (moveAllBalls)// while moveAllBalls is true
			{
				thisBall.move();// moves the individual ball
				myPanel.repaint();// repaints the canvas
				try 
				{
					Thread.sleep(UpdateRate);
				} catch (InterruptedException e) 
				{
					// System.out.println("Cannot sleep to move the ball");
				}
				if (Thread.interrupted())
					break;
			}
		}
	}
}
