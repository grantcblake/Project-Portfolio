// *********************************************
//  Name: Grant Blake           Folder Name:  Blakeg_L6
//  CS162  Spring 2010          Lab #: 6
//  Tue/Thu 1:00
//  Date: 5/18/10
//
//   Program Name: Car.java
//   Program Description:
//	Draws a car and uses a timer to make it drive across the screen.
//	Turns around and drives back and forth.
//**********************************************
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Car
{
	public static void main(String[] args)
   {
	   JFrame frame = new JFrame ("Car");
	   frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

	   frame.getContentPane().add(new CarPanel());
	   frame.pack();
	   frame.setVisible(true);
    }
}