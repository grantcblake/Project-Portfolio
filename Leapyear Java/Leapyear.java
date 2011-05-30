// *********************************************
//  Name: Grant Blake           Folder Name:  Blakeg_L6
//  CS161  Winter 2010          Lab #: 6
//  Mon/Wed 10:00
//  Date: 2/18/10
//
//   Program Name: Leapyear.java
//   Program Description:
//      Accepts user input of an integer representing a year (after 1582) and returns
//      whether or not that year is a leap year.
//	
//	TEST ORACLE
//	-----------
//	Year	leap year?
//	1582	no
//	1583	no
//	1584	yes
//	1585	no
//	1586	no
//	1587	no
//	1588	yes
//	1589	no
//	1590	no
//	1591	no
//	1592	yes
//**********************************************

import java.util.Scanner;

public class Leapyear
{

	public static void main(String[] args)
	{

		Scanner input = new Scanner(System.in);
		int year, remain;
		char go = 'y';
		String ans;



		do
		{

			System.out.println("Give me an integer year after 1582 and I will tell you if it is a leap year.");
			year = input.nextInt();

			if (year >= 1582)
			{
				remain = year%100;
				if (remain == 0)
				{	remain = year%400;
					if (remain == 0)
					{
						System.out.println (year+ " is indeed a leap year.");

					}

					else
					{
						System.out.println (year+ " is not a leap year.");

					}
				}

				else
				{	remain = year%4;
					if (remain == 0)
					{
						System.out.println (year+ " is indeed a leap year.");

					}

					else
					{
						System.out.println (year+ " is not a leap year.");

					}
				}

			}

			else
			{	System.out.println ("Please try entering an integer after year 1582.");
				year = input.nextInt();
			}
			System.out.println("Would you like to try another year? (y/n)");
			ans = input.next();
			go = ans.charAt(0);

		} while (go == 'y');//as long as the user enters 'y' when prompted, the program will continue to run.


	}

}
