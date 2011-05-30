// *********************************************
//  Name: Grant Blake           Folder Name:  Blakeg_L8
//  CS161  Winter 2010          Lab #: 8
//  Mon/Wed 10:00
//  Date: 2/25/10
//
//   Program Name: GolfScores.java
//   Program Description:
//		Reads golf scores from a text file and prints a table with scores for each player for each hole.
//		Prints final scores out to screen, and then determines the winner and displays that in a message.
//
//	TEST ORACLE
//	-----------
//	input (golf.txt)
//	3 5 5 4 6
//	3 4 5 5 5
//	4 6 5 4 7
//	3 3 4 4 5
//
//	outcome (GolfScores.java)
//	Hole | Par | Player 1     | Player 2     | Player 3     | Player 4     |
//	------------------------------------------------------------------------
//	1    |  3  | 2 over par.  | 2 over par.  | 1 over par.  | 3 over par.  |
//	------------------------------------------------------------------------
//	2    |  3  | 1 over par.  | 2 over par.  | 2 over par.  | 2 over par.  |
//	------------------------------------------------------------------------
//	3    |  4  | 2 over par.  | 1 over par.  | Scored par!  | 3 over par.  |
//	------------------------------------------------------------------------
//	4    |  3  | Scored par!  | 1 over par.  | 1 over par.  | 2 over par.  |
//	------------------------------------------------------------------------
//	Final Scores
//	------------
//	Player 1: 5
//	Player 2: 6
//	Player 3: 4
//	Player 4: 10
//
//	And the winner is...
//
//	                 Player 3!
//
//**********************************************

import java.util.Scanner;
import java.io.*;

public class GolfScores
{
	public static int min (int s1, int s2, int s3, int s4)
	{
		int smallest = s1;

		if (smallest > s2)
			smallest = s2;
		if (smallest > s3)
			smallest = s3;
		if (smallest > s4)
			smallest = s4;
		return smallest;
	}


	public static void main (String[]args) throws IOException
	{
		Scanner fileScan = new Scanner (new File("golf.txt"));

		Scanner scoreScan;
		String score;

		String[] scores = new String[5];
		String[] par = new String[18];
		String[] p1 = new String[18];
		String[] p2 = new String[18];
		String[] p3 = new String[18];
		String[] p4 = new String[18];
		int hole = 0, p1Total = 0, p2Total = 0, p3Total = 0, p4Total = 0;
		int p1Hole, p2Hole, p3Hole, p4Hole, parHole, parTotal = 0;

		System.out.println ("Hole | Par | Player 1     | Player 2     | Player 3     | Player 4     |");
		System.out.println ("------------------------------------------------------------------------");

		while(fileScan.hasNext())
		{
			score = fileScan.nextLine();

			scoreScan = new Scanner (score);

			for (int count = 0; count<5; count++)
			{
				scores[count] = scoreScan.next();
			}

			par[hole] = scores[0];
			p1[hole] = scores[1];
			p2[hole] = scores[2];
			p3[hole] = scores[3];
			p4[hole] = scores[4];

			p1Hole = Integer.parseInt(p1[hole]);
			p2Hole = Integer.parseInt(p2[hole]);
			p3Hole = Integer.parseInt(p3[hole]);
			p4Hole = Integer.parseInt(p4[hole]);
			parHole = Integer.parseInt(par[hole]);

			int p1Score = (p1Hole - parHole);
			int p2Score = (p2Hole - parHole);
			int p3Score = (p3Hole - parHole);
			int p4Score = (p4Hole - parHole);

			p1Total += p1Hole;
			p2Total += p2Hole;
			p3Total += p3Hole;
			p4Total += p4Hole;
			parTotal += parHole;

			if (hole > 8)
				System.out.print (" " + (hole + 1) + "  |");
			else
				System.out.print (" " + (hole + 1) + "   |");
			System.out.print ("  " + parHole + "  |");
			if (p1Score < 0)
				System.out.print (" " + Math.abs(p1Score) + " under par!!|");
			else
				if (p1Score == 0)
					System.out.print (" Scored par!  |");
				else
					System.out.print (" " + p1Score + " over par.  |");
			if (p2Score < 0)
				System.out.print (" " + Math.abs(p2Score) + " under par!!|");
			else
				if (p2Score == 0)
					System.out.print (" Scored par!  |");
				else
					System.out.print (" " + p2Score + " over par.  |");
			if (p3Score < 0)
				System.out.print (" " + Math.abs(p3Score) + " under par!!|");
			else
				if (p3Score == 0)
					System.out.print (" Scored par!  |");
				else
					System.out.print (" " + p3Score + " over par.  |");
			if (p4Score < 0)
				System.out.print (" " + Math.abs(p4Score) + " under par!!|\n");
			else
				if (p4Score == 0)
					System.out.print (" Scored par!  |\n");
				else
					System.out.print (" " + p4Score + " over par.  |\n");
 			System.out.println ("------------------------------------------------------------------------");

			hole++;
		}

		System.out.println ("Final Scores");
		System.out.println ("------------");
		System.out.println ("Player 1: " + (p1Total - parTotal));
		System.out.println ("Player 2: " + (p2Total - parTotal));
		System.out.println ("Player 3: " + (p3Total - parTotal));
		System.out.println ("Player 4: " + (p4Total - parTotal));

		int winner = GolfScores.min(p1Total, p2Total, p3Total, p4Total);

		if (winner == p1Total)
			System.out.println ("\nAnd the winner is...\n\n                 Player 1!\n");
		if (winner == p2Total)
			System.out.println ("\nAnd the winner is...\n\n                 Player 2!\n");
		if (winner == p3Total)
			System.out.println ("\nAnd the winner is...\n\n                 Player 3!\n");
		if (winner == p4Total)
			System.out.println ("\nAnd the winner is...\n\n                 Player 4!\n");
	}
}