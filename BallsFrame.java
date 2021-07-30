/**
 * @Author dewibrightman
 * @version 1
 * @Date: 25/11/2019
 * @Title: Lab 8 - Threaded Balls
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BallsFrame extends JFrame 
{
	private BallPanel BallPanel; // panel with the balls
	private final int FRAME_WIDTH = 600;
	private final int FRAME_HEIGHT = 500;
	public static int ballPanelHeight; // size of the pane
	public static int ballPanelWidth; // size of the pane
	private int initialSliderValue = 3;
	private int minSliderValue = 1;
	private int maxSliderValue = 5;

	public BallsFrame() 
	{
		setVisible(true);
		setTitle("Ball Viewer");
		BallPanel = new BallPanel();
		JPanel bottomPanel = createBottomPanel();

		add(BallPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
//		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		pack();

		setResizable(false);
		ballPanelHeight = BallPanel.getHeight();
		ballPanelWidth = BallPanel.getWidth();
	}

	/**
	 * Creates the bottom panel
	 * 
	 * @return the panel with buttons and a slider
	 */
	private JPanel createBottomPanel() 
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));// Horizontal flow
		panel.add(createButtonsPanel());// adds button panel
		panel.add(createSlider()); // adds the slider
		return panel;
	}

	/**
	 * Creates a JSlider, initialises it and
	 * 
	 * @return the JSlider
	 */
	private JSlider createSlider() 
	{

		/* Create a panel to store the slider */
		JSlider slider = new JSlider(minSliderValue, maxSliderValue);
		slider.setValue(initialSliderValue); // Set value to slider initial value
		BallRunnable.setUpdateRate((maxSliderValue + 1) - initialSliderValue);

		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent ce) 
			{
				int val = ((JSlider) ce.getSource()).getValue();// get the current value
				BallRunnable.setUpdateRate((maxSliderValue + 1) - val);// reverses the value in order to get
																				// slower display on the left
			}
		});

		return slider;
	}

	/**
	 * Creates a jPanel with 3 buttons
	 * 
	 * @return the JPanel
	 */
	private JPanel createButtonsPanel() 
	{

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3, 10, 10));
		// Create the buttons
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				System.out.println("Start");
				Ball ball = new Ball();// creates new coloured ball
				BallPanel.addBall(ball); // adds the ball to the pane
			}
		});

		JButton pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				System.out.println("Pause");
				BallPanel.pauseBalls();// pauses balls
			}
		});
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				System.out.println("Stop");
				BallPanel.stopBalls();// stops balls
			}
		});
		panel.add(startButton);
		panel.add(pauseButton);
		panel.add(stopButton);

		return panel;
	}

	//Main method
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run() {
				JFrame frame = new BallsFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
