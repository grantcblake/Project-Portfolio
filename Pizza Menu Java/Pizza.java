// *********************************************
//  Name: Grant Blake           Folder Name:  Blakeg_L5
//  CS162  Spring 2010          Lab #: 5
//  Tue/Thu 1:00
//  Date: 5/11/10
//
//   Program Name: Pizza.java
//   Program Description:
//	A menu to order a pizza, sides, and drinks on the computer.
//	Updates totals in the side panel.
//**********************************************
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.border.*;

public class Pizza
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame ("Pizza Order Form");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		MainPanel panel = new MainPanel();

		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}//end method main
}//end class Pizza

class MainPanel extends JPanel
{
	public double pPrice, sPrice, dPrice;
	CostPanel Cost; PizzaPanel Pizza; SidesPanel Sides; DrinksPanel Drinks;
	public MainPanel()
	{
		setLayout (new BorderLayout());

		setBackground (Color.lightGray);
		setPreferredSize (new Dimension(600, 400));

		Pizza = new PizzaPanel();
		Sides = new SidesPanel();
		Drinks = new DrinksPanel();

		JTabbedPane tp = new JTabbedPane();
		tp.setBackground (Color.yellow);
		tp.addTab ("Welcome", new WelcomePanel());
		tp.addTab ("Pizza", Pizza);
		tp.addTab ("Sides", Sides);
		tp.addTab ("Drinks", Drinks);

		ImageIcon PizzaImage = new ImageIcon ("pizzapic.jpg");

		JLabel top = new JLabel ("Grant's Pizza Parlour", PizzaImage, SwingConstants.CENTER);
		Cost = new CostPanel();

		add (tp, BorderLayout.CENTER);
		add (top, BorderLayout.NORTH);
		add (Cost, BorderLayout.EAST);

		setPrice();
	}//end constructor MainPanel

	public void setPrice()
	{
		pPrice = Pizza.getPizza();
		sPrice = Sides.getSides();
		dPrice = Drinks.getDrinks();

		Cost.setCost (pPrice, sPrice, dPrice);
	}

}//end class MainPanel

class WelcomePanel extends JPanel
{
	public WelcomePanel()
	{
		JLabel l1 = new JLabel ("Welcome to Grant's Pizza Parlour.");
		JLabel l2 = new JLabel ("This program will help you order your pizza and any sides or drinks you want.");

		add (l1);
		add (l2);
	}//end constructor WelcomePanel
}//end class WelcomePanel

class PizzaPanel extends JPanel
{
	private double PizzaTotal, sizeCost, toppingCost;
	public JCheckBox cheese, pep, saus, cbacon, papple, chick, onion, pepper, olive;
	private JRadioButton small, medium, large;
	public PizzaPanel()
	{
		setLayout (new BorderLayout());
		ToppingListener tl = new ToppingListener();
		SizeListener szl = new SizeListener();

		JPanel topping = new JPanel();
		topping.setLayout (new GridLayout (3,3));
		cheese = new JCheckBox ("Cheese ($Free$)");
		pep = new JCheckBox ("Pepperoni");
		saus = new JCheckBox ("Sausage");
		cbacon = new JCheckBox ("Canadian Bacon");
		papple = new JCheckBox ("Pineapple");
		chick = new JCheckBox ("Barbecue Chicken");
		onion = new JCheckBox ("Onion");
		pepper = new JCheckBox ("Peppers");
		olive = new JCheckBox ("Olives");

		cheese.addItemListener(tl);
		pep.addItemListener(tl);
		saus.addItemListener(tl);
		cbacon.addItemListener(tl);
		papple.addItemListener(tl);
		chick.addItemListener(tl);
		onion.addItemListener(tl);
		pepper.addItemListener(tl);
		olive.addItemListener(tl);

		topping.add (cheese);
		topping.add (pep);
		topping.add (saus);
		topping.add (chick);
		topping.add (cbacon);
		topping.add (papple);
		topping.add (onion);
		topping.add (pepper);
		topping.add (olive);
		Border b1 = BorderFactory.createEmptyBorder (8, 10, 8, 10);
		TitledBorder b2 = BorderFactory.createTitledBorder ("Toppings - $1.50 each");
		topping.setBorder (BorderFactory.createCompoundBorder (b1, b2));


		JPanel size = new JPanel();

		small = new JRadioButton ("Small - $8.00");
		medium = new JRadioButton ("Medium - $10.00");
		large = new JRadioButton ("Large - $12.00");
		ButtonGroup group = new ButtonGroup();
		group.add (small);
		group.add (medium);
		group.add (large);
		size.add (small);
		size.add (medium);
		size.add (large);
		small.addActionListener(szl);
		medium.addActionListener(szl);
		large.addActionListener(szl);
		Border b3 = BorderFactory.createEmptyBorder (8, 10, 0, 10);
		TitledBorder b4 = BorderFactory.createTitledBorder ("Size");
		size.setBorder (BorderFactory.createCompoundBorder (b3, b4));
		add (size, BorderLayout.NORTH);
		add (topping, BorderLayout.CENTER);
	}//end constructor PizzaPanel

	private class SizeListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
			Object source = event.getSource();
			if (source == small)
				sizeCost = 8;
			else
				if (source == medium)
					sizeCost = 10;
				else
					if (source == large)
						sizeCost = 12;
					else
						sizeCost = 0;
			setPizza();

			JFrame parent = (JFrame)(small.getTopLevelAncestor());//gets top JFrame
			MainPanel panel = (MainPanel)parent.getContentPane().getComponent(0);//gets MainPanel

			panel.setPrice();//calls method to update all the prices

		}//end method actionPerformed
	}//end class SizeListener

	private class ToppingListener implements ItemListener
	{
		public void itemStateChanged (ItemEvent event)
		{
			double t1=0, t2=0, t3=0, t4=0, t5=0, t6=0, t7=0, t8=0, t9=0;

			if (cheese.isSelected())
				t1 = 0;
			if (pep.isSelected())
				t2 = 1.5;
			if (saus.isSelected())
				t3 = 1.5;
			if (cbacon.isSelected())
				t4 = 1.5;
			if (papple.isSelected())
				t5 = 1.5;
			if (chick.isSelected())
				t6 = 1.5;
			if (onion.isSelected())
				t7 = 1.5;
			if (pepper.isSelected())
				t8 = 1.5;
			if (olive.isSelected())
				t9 = 1.5;

			toppingCost = t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9;

			setPizza();

			JFrame parent = (JFrame)(cheese.getTopLevelAncestor());//gets top JFrame
			MainPanel panel = (MainPanel)parent.getContentPane().getComponent(0);//gets MainPanel

			panel.setPrice();//calls method to update all the prices
		}//end method itemStateChanged
	}//end class ToppingListener

	public double getPizza()
	{
		return PizzaTotal;
	}//end method getPizza

	public void setPizza()
	{
		if (sizeCost != 0)
			PizzaTotal = sizeCost + toppingCost;
	}//end method setPizza

}//end class PizzaPanel

class SidesPanel extends JPanel
{
	public double SidesTotal;
	private JRadioButton garl1, garl2, garl3, garl4, cheese1, cheese2, cheese3, cheese4, cinn1, cinn2, cinn3, cinn4;
	public SidesPanel()
	{
		setLayout (new GridLayout (0, 3, 5, 5));
		setBorder (BorderFactory.createEmptyBorder (8, 10, 8, 10));
		SidesListener sl = new SidesListener();

		JPanel garl = new JPanel();
		garl1 = new JRadioButton ("Order of  5 - $2.50");
		garl2 = new JRadioButton ("Order of 10 - $4.50");
		garl3 = new JRadioButton ("Order of 15 - $6.00");
		garl4 = new JRadioButton ("None, Thank You  :)");
		ButtonGroup g1 = new ButtonGroup();
		g1.add (garl1);
		g1.add (garl2);
		g1.add (garl3);
		g1.add (garl4);
		garl.add (garl1);
		garl.add (garl2);
		garl.add (garl3);
		garl.add (garl4);
		garl.setBorder (BorderFactory.createTitledBorder ("Garlic Bread Sticks"));
		garl4.doClick();

		JPanel cheese = new JPanel();
		cheese1 = new JRadioButton ("Order of  5 - $3.00");
		cheese2 = new JRadioButton ("Order of 10 - $5.50");
		cheese3 = new JRadioButton ("Order of 15 - $8.00");
		cheese4 = new JRadioButton ("None, Thank You  :)");
		ButtonGroup g2 = new ButtonGroup();
		g2.add (cheese1);
		g2.add (cheese2);
		g2.add (cheese3);
		g2.add (cheese4);
		cheese.add (cheese1);
		cheese.add (cheese2);
		cheese.add (cheese3);
		cheese.add (cheese4);
		cheese.setBorder (BorderFactory.createTitledBorder ("Cheesy Bread Sticks"));
		cheese4.doClick();

		JPanel cinn = new JPanel();
		cinn1 = new JRadioButton ("Order of  5 - $2.50");
		cinn2 = new JRadioButton ("Order of 10 - $4.50");
		cinn3 = new JRadioButton ("Order of 15 - $6.00");
		cinn4 = new JRadioButton ("None, Thank You  :)");
		ButtonGroup g3 = new ButtonGroup();
		g3.add (cinn1);
		g3.add (cinn2);
		g3.add (cinn3);
		g3.add (cinn4);
		cinn.add (cinn1);
		cinn.add (cinn2);
		cinn.add (cinn3);
		cinn.add (cinn4);
		cinn.setBorder (BorderFactory.createTitledBorder ("Cinnamon Bread Sticks"));
		cinn4.doClick();

		garl1.addActionListener(sl);
		garl2.addActionListener(sl);
		garl3.addActionListener(sl);
		garl4.addActionListener(sl);
		cheese1.addActionListener(sl);
		cheese2.addActionListener(sl);
		cheese3.addActionListener(sl);
		cheese4.addActionListener(sl);
		cinn1.addActionListener(sl);
		cinn2.addActionListener(sl);
		cinn3.addActionListener(sl);
		cinn4.addActionListener(sl);

		add (garl);
		add (cheese);
		add (cinn);
	}//end constructor SidesPanel

	private class SidesListener implements ActionListener
	{
		double g = 0, ch = 0, ci = 0;
		public void actionPerformed (ActionEvent event)
		{
			Object source = event.getSource();


			if (source == garl1)
				g = 2.5;
			else
				if (source == garl2)
					g = 4.5;
				else
					if (source == garl3)
						g = 6;
					else
						if (source == garl4)
							g = 0;
			if (source == cheese1)
				ch = 3;
			else
				if (source == cheese2)
					ch = 5.5;
				else
					if (source == cheese3)
						ch = 8;
					else
						if (source == cheese4)
							ch = 0;
			if (source == cinn1)
				ci = 2.5;
			else
				if (source == cinn2)
					ci = 4.5;
				else
					if (source == cinn3)
						ci = 6;
					else
						if (source == cinn4)
							ci = 0;

			SidesTotal = g + ch + ci;

			JFrame parent = (JFrame)(garl4.getTopLevelAncestor());//gets top JFrame
			MainPanel panel = (MainPanel)parent.getContentPane().getComponent(0);//gets MainPanel

			panel.setPrice();//calls method to update all the prices
		}//end method actionPerformed
	}//end class SidesListener

	public double getSides()
	{
		return SidesTotal;
	}//end method getSides
}//end class SidesPanel

class DrinksPanel extends JPanel
{
	public double DrinksTotal;
	public JCheckBox pepsi, dpepsi, cherry, mist, dew, mug, tropo, tropg, trops;
	public DrinksPanel()
	{
		setLayout (new GridLayout (3,3));
		DrinksListener dl = new DrinksListener();

		pepsi = new JCheckBox ("Pepsi");
		dpepsi = new JCheckBox ("Diet Pepsi");
		cherry = new JCheckBox ("Wild Cherry Pepsi");
		mist = new JCheckBox ("Sierra Mist");
		dew = new JCheckBox ("Mountain Dew");
		mug = new JCheckBox ("Mug Root Beer");
		tropo = new JCheckBox ("Tropicana Orange");
		tropg = new JCheckBox ("Tropicana Grape");
		trops = new JCheckBox ("Tropicana Strawberry");

		add (pepsi);
		add (mug);
		add (tropo);
		add (dpepsi);
		add (dew);
		add (tropg);
		add (cherry);
		add (mist);
		add (trops);
		pepsi.addItemListener(dl);
		dpepsi.addItemListener(dl);
		cherry.addItemListener(dl);
		mist.addItemListener(dl);
		dew.addItemListener(dl);
		mug.addItemListener(dl);
		tropo.addItemListener(dl);
		tropg.addItemListener(dl);
		trops.addItemListener(dl);


		Border b1 = BorderFactory.createEmptyBorder (8, 10, 8, 10);
		TitledBorder b2 = BorderFactory.createTitledBorder ("Two Liter Bottles of Soda - $2.00 each");
		setBorder (BorderFactory.createCompoundBorder (b1, b2));
	}//end constructor DrinksPanel

	private class DrinksListener implements ItemListener
	{
		public void itemStateChanged (ItemEvent event)
		{
			double d1=0, d2=0, d3=0, d4=0, d5=0, d6=0, d7=0, d8=0, d9=0;

			if (pepsi.isSelected())
				d1 = 2;
			if (dpepsi.isSelected())
				d2 = 2;
			if (cherry.isSelected())
				d3 = 2;
			if (mist.isSelected())
				d4 = 2;
			if (dew.isSelected())
				d5 = 2;
			if (mug.isSelected())
				d6 = 2;
			if (tropo.isSelected())
				d7 = 2;
			if (tropg.isSelected())
				d8 = 2;
			if (trops.isSelected())
				d9 = 2;

			DrinksTotal = d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9;

			JFrame parent = (JFrame)(pepsi.getTopLevelAncestor());//gets top JFrame
			MainPanel panel = (MainPanel)parent.getContentPane().getComponent(0);//gets MainPanel

			panel.setPrice();//calls method to update all the prices
		}//end method itemStateChanged
	}//end class DrinksListener

	public double getDrinks()
	{
		return DrinksTotal;
	}//end method getDrinks
}//end class DrinksPanel

class CostPanel extends JPanel
{
	public double pizza, sides, drinks, total;
	public JLabel l1, l2, l3, l4;
	NumberFormat fmt;
	public CostPanel()
	{
		setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));

		setBackground (Color.green);



		fmt = NumberFormat.getCurrencyInstance();
		pizza = 0;
		sides = 0.00;
		drinks = 0.00;
		total = (pizza + sides + drinks);

		l1 = new JLabel ("Pizza Cost: " + fmt.format(pizza) + "   ");
		l2 = new JLabel ("Sides Cost: " + fmt.format(sides) + "   ");
		l3 = new JLabel ("Drinks Cost: " + fmt.format(drinks) + "  ");
		l4 = new JLabel ("Total Cost: " + fmt.format(total) + "   ");

		add (Box.createRigidArea (new Dimension(0, 40)));
		add (l1);
		add (Box.createVerticalGlue());
		add (l2);
		add (Box.createVerticalGlue());
		add (l3);
		add (Box.createVerticalGlue());
		add (Box.createRigidArea (new Dimension(0, 20)));
		add (l4);
		add (Box.createRigidArea (new Dimension(0, 20)));
	}//end constructor CostPanel

	public void setCost(double pTotal, double sTotal, double dTotal)
	{
		pizza = pTotal;
		sides = sTotal;
		drinks = dTotal;
		total = (pizza + sides + drinks);

		l1.setText("Pizza Cost: " + fmt.format(pizza) + "   ");
		l2.setText("Sides Cost: " + fmt.format(sides) + "   ");
		l3.setText("Drinks Cost: " + fmt.format(drinks) + "  ");
		l4.setText("Total Cost: " + fmt.format(total) + "   ");
	}
}//end class CostPanel