import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CarPanel extends JPanel
{
	private int Delay = 35, Width = 800, Height = 120;
	private int X = -80, Y = 80;
	private int moveX = 10, moveY = 0;

	private int[] xCar = {-95, -95, -80, -75, -35, -30, 0, 0};
	private int[] yCar = {Y+20, Y-2, Y, Y-15, Y-15, Y, Y+5, Y+20};
	Polygon car = new Polygon (xCar, yCar, xCar.length);
	private int[] xrc = {Width, Width, Width+30, Width+35, Width+75, Width+80, Width+95, Width+95};
	private int[] yrc = {Y+20, Y+5, Y, Y-15, Y-15, Y, Y-2, Y+20};

	private int[] xWin1 = {-60, -37, -33, -60};
	private int[] yWin1 = {Y-13, Y-13, Y, Y};
	Polygon win1 = new Polygon (xWin1, yWin1, xWin1.length);
	private int[] xrw1 = {Width+60, Width+33, Width+37, Width+60};
	private int[] yrw1 = {Y, Y, Y-13, Y-13};

	private int[] xWin2 = {-74, -63, -63, -78};
	private int[] yWin2 = {Y-13, Y-13, Y, Y};
	Polygon win2 = new Polygon (xWin2, yWin2, xWin2.length);
	private int[] xrw2 = {Width+78, Width+63, Width+63, Width+74};
	private int[] yrw2 = {Y, Y, Y-13, Y-13};

	private Timer timer;

	private Color darkBlue = new Color (0, 0, 100);

	public CarPanel()
	{
		timer = new Timer (Delay, new DriverListener());

		setPreferredSize (new Dimension(Width, Height));
		setBackground (darkBlue);
		timer.start();
	}

	public void paintComponent (Graphics page)
	{
		super.paintComponent (page);

		page.setColor (Color.darkGray);
		page.fillRect (0, 90, Width, Height);

		page.setColor (Color.red);
		page.fillPolygon (car);

		page.setColor (Color.cyan);
		page.fillPolygon (win1);
		page.fillPolygon (win2);

		page.setColor (Color.black);
		page.fillOval (X-5, Y+10, 20, 20);
		page.fillOval (X+50, Y+10, 20, 20);

		page.setColor (Color.white);
		page.fillOval (X-4, Y+11, 18, 18);
		page.fillOval (X+51, Y+11, 18, 18);

		page.setColor (Color.black);
		page.fillOval (X-1, Y+14, 12, 12);
		page.fillOval (X+54, Y+14, 12, 12);
	}

	private class DriverListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
			car.translate(moveX, moveY);
			win1.translate(moveX, moveY);
			win2.translate(moveX, moveY);

			X += moveX;

			if (X <= -80)
			{
				moveX = moveX * -1;
				Polygon temp = new Polygon(xCar, yCar, xCar.length);
				car = temp;
				temp = new Polygon(xWin1, yWin1, xWin1.length);
				win1 = temp;
				temp = new Polygon(xWin2, yWin2, xWin2.length);
				win2 = temp;
			}

			if (X >= Width+11)
			{
				moveX = moveX * -1;
				Polygon temp = new Polygon(xrc, yrc, xrc.length);
				car = temp;
				temp = new Polygon(xrw1, yrw1, xrw1.length);
				win1 = temp;
				temp = new Polygon(xrw2, yrw2, xrw2.length);
				win2 = temp;
			}


			repaint();
		}
	}

}