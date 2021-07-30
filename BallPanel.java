/**
 * @Author dewibrightman
 * @version 1
 * @Date: 25/11/2019
 * @Title: Lab 8 - Threaded Balls
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class BallPanel extends JPanel 
{
	ArrayList<Ball> allBalls = new ArrayList<Ball>(); // creates a arraylist of Balls
	ArrayList<Thread> allThreads = new ArrayList<Thread>(); // creates an arraylist of runnables
	private int stoppingMiliseconds = 5000;

	public BallPanel()
	{}

	/**
	 * paints the ball component using
	 * 
	 * @param g Graphics
	 */
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		boolean hit = false;// default hit to false
		for (Ball b1 : allBalls) 
		{
			b1.draw(g2);
			for (Ball b2 : allBalls)
			{
				if (b1 != b2)// makes sure we are not referencing the same
				{
					hit = checkCollision(b1.getCenter().width, b1.getCenter().height, b2.getCenter().width,
							b2.getCenter().height, b1.getRadius(), b2.getRadius());
					if (hit) 
					{
						Thread t1 = allThreads.get(allBalls.indexOf(b1));
						Thread t2 = allThreads.get(allBalls.indexOf(b2));
						allThreads.remove(t1);// remove the thread
						allThreads.remove(t2);
						t1.interrupt();// stop the thread
						t2.interrupt();

						allBalls.remove(b1);// removes balls from the arraylist
						allBalls.remove(b2);

						break;// breaks the first loop
					}
				}
			}
			if (hit)
				break; // breaks the second loop
		}
	}

	/**
	 * checks if 2 balls intersect
	 * 
	 * @param x1 centre x of ball 1
	 * @param y1 centre y of ball 1
	 * @param x2 centre x of ball 2
	 * @param y2 centre y of ball 2
	 * @param r1 radius of ball 1
	 * @param r2 radius of ball2
	 * @return
	 */
	private boolean checkCollision(int x1, int y1, int x2, int y2, int r1, int r2) 
	{
		// euclidean distance
		double distance = Math.sqrt((double) Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
		return distance <= r1 + r2;
	}

	/**
	 * adds a ball to the panel
	 * 
	 * @param thisBall
	 */
	public void addBall(Ball thisBall)
	{
		if (BallRunnable.getMoveBall())
		{
			BallRunnable r = new BallRunnable(thisBall, this);
			Thread t = new Thread(r);
			t.start();
			allBalls.add(thisBall);
			allThreads.add(t);
		} else
			BallRunnable.setMoveBall(true);
	}

	/**
	 * pauses all a runnables with the static variable moveBall
	 */
	public void pauseBalls() 
	{
		BallRunnable.setMoveBall(false);
	}

	/**
	 * Stops the balls with a 5 second delay
	 */
	public void stopBalls() 
	{
		BallRunnable.setMoveBall(false);
		try {
			Thread.sleep(stoppingMiliseconds);
		} catch (InterruptedException e) 
		{
			System.out.println("Error in trying to stop the balls");
		}
		for (Thread t : allThreads) 
		{
			t.interrupt();// Interrupts the thread - stops them
		}
		allBalls.clear();// clears the arraylist
		allThreads.clear(); // clears the arraylist
		repaint();

		BallRunnable.setMoveBall(true);// sets move ball to true
	}
}
