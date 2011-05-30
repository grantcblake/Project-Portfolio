// *********************************************
//  Name: Grant Blake           Folder Name:  Blakeg_L3
//  CS162  Spring 2010          Lab #: 3
//  Tue/Thu 1:00
//  Date: 4/15/10
//
//   Program Name: Connect4.java
//   Program Description:
//
//
//**********************************************
import java.io.*;
import java.util.Scanner;

public class Connect4
{
	public final static int NOPLAYER = 0;
	public final static int PLAYER1 = 1;
	public final static int PLAYER2 = 2;
	public final static int DRAW = 3;

	//--------------------------------------------------
	//	Main driver for the game.
	//--------------------------------------------------

	public static void main(String[] args) throws Exception
	{
		char again = 'Y';
		int player = PLAYER2;
		int winner = NOPLAYER;
		Scanner scan = new Scanner(System.in);
		Board myBoard;
		Record[] record = new Record[25];

		int moves = 0;//number of moves played each game
		int games = 0;//number of games played

		while (again == 'Y' && games < 25)
		{
			myBoard = new Board();
			winner = NOPLAYER;
			moves = 0;//reset moves after each game
			while (winner == NOPLAYER)
			{
				player = (player == PLAYER1) ? PLAYER2 : PLAYER1;//switch players
				boolean valid = false;
				while(!valid)
				{
					myBoard.print();//prints out current game board
					System.out.println("Player " + player + "'s turn.  Choose the column to drop your piece in (0-7)");
					int column = scan.nextInt();
					valid = myBoard.move(player, column);//checks if the move is valid
					if (!valid)
						System.out.println ("\nThat move is not valid.  Try again please :]");//error message for invalid input
				}

				winner = myBoard.check_win();//check for a winner
				moves++;//keep track of moves made
				if (moves == 64 && winner == NOPLAYER)
					winner = DRAW;
			} //end current game

			record[games] = new Record(winner, moves);
			myBoard.print();//prints ending board
			games++;//keep track of games played
			if (winner == DRAW)
				System.out.println ("The Game is a draw.");//message for a draw
			else
				System.out.println ("Congratulations Player " + winner + "!!!");//winning message

			//prompts for another game
			System.out.print ("\nDo you want to play another game? (y/n): ");
			String word = scan.next();
			word = word.toUpperCase();
			again = word.charAt(0);
			System.out.println("\n\n\n\n\n\n\n\n");//extra space in between games (less clutter)

		} //end game series

		int game = 0;//steps through the loop printing - not to be confused with games
		System.out.println ("Game score summary:");
		System.out.println ("===================\n");
		while (game < games)
		{
			System.out.println ("Game " + (game + 1) + ": " + record[game].print());
			game++;
		}
    } //end main method
} //end class Connect4

//======================================================
//	Represents one player piece.
//======================================================

class Piece
{
	private int owner;

	//--------------------------------------------------
	//	Sets up a new piece, owned by neither player
	//--------------------------------------------------

	public Piece()
	{
		owner = Connect4.NOPLAYER;
	} //end constructor Piece

	public void setOwner(int player)
	{
		owner = player;
	}//end method setOwner

	public int getOwner()
	{
		return owner;
	}//end method getOwner

	public boolean isUsed()
	{
		if (owner == Connect4.NOPLAYER)
			return false;
		else
			return true;
	}//end method isUsed

	public String print()
	{
		String it = " ";

		if (owner == Connect4.NOPLAYER)
			it = " ";
		if (owner == Connect4.PLAYER1)
			it = "X";
		if (owner == Connect4.PLAYER2)
			it = "O";

		return it;
	}//end method print

} // end class Piece

//======================================================
//	Represents a game board.
//======================================================

class Board
{

	Piece[][] board = new Piece[8][8];

	public Board()
	{
		for(int row = 0; row < 8; row++)
			for(int column = 0; column < 8; column++)
			{
				board[row][column] = new Piece();
			}
	}//end constructor board

	//checks for valid moves, completes the moves if valid
	public boolean move(int player, int column)
	{
		 if (column > 7 || column < 0)
		 	return false;
		 int n = 7;
		 if (board[0][column].isUsed())
		 {
			 return false;
		 }
		 else
		 {
			 while(board[n][column].isUsed())
			 {
				 n--;
			 }
			 board[n][column].setOwner(player);
			 return true;
		 }

	 }//end method move

	//--------------------------------------------------
	//	Checks if there are any winners at this point.
	//--------------------------------------------------

	public boolean is_winner(int player)
	{
		int check = 0;//var to track adjacent pieces
		int h = 0;//var to move through the columns
		int v = 7;//var to move through the rows

		//check for horizontal wins
		while (check <4 && v >= 0)//stays in the loop until a win is discovered or it runs out of spots
		{
			if (board[v][h].getOwner() == player)
				check++;
			else
				check = 0;
			h++;

			if (h > 7)//jumps to a new column when it reaches the end
			{
				h = 0;
				v--;
				check = 0;
			}
		}//end while loop

		h = 0; v = 7;//reset to the bottom left corner
		if (check < 4)
			check = 0;//reset check var if a win has not already been found

		//check for vertical wins
		while (check < 4 && h <= 7)//stays in the loop until a win is discovered or it runs out of spots
		{
			if (board[v][h].getOwner() == player)
				check++;
			else
				check = 0;
			v--;

			if (v < 0)//jumps to a new row when it reaches the end
			{
				v = 7;
				h++;
				check = 0;
			}
		}//end while loop

		return (check == 4) ? true : false;
	}//end method is_winner

	public int check_win()
	{
		//int result = Connect4.NOPLAYER;
		if(is_winner(Connect4.PLAYER1))
			return Connect4.PLAYER1;
		if(is_winner(Connect4.PLAYER2))
			return Connect4.PLAYER2;
		else
			return Connect4.NOPLAYER;
	} // end method check_win

	//prints the board
	public void print()
	{
		String show = " 0 1 2 3 4 5 6 7 \n";

		for (int row = 0; row < 8; row++)
		{
			for (int column = 0; column < 8; column++)
			{
				show += "|";
				show += board[row][column].print();
			}
			show += "|\n";
		}

		System.out.println(show);
	}//end method print

} // end class Board


//==========================================
//Keeps records of wins and moves made
//==========================================

class Record
{
	private int player, numMoves;

	public Record (int winner, int moves)
	{
		player = winner;
		numMoves = moves;
	}//end constructor Record

	public String print()
	{
		String message = "Player ";
		message += player;
		message += " won in ";
		message += numMoves;
		message += " moves!";

		if (player == Connect4.DRAW)//deals with draws
			message = "Cat's game!";

		return message;
	}//end method print
}//end class Record