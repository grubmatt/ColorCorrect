package com.colorcorrect.fifteenboard;

import android.graphics.Color;

import com.colorcorrect.framework.Game;
import com.colorcorrect.framework.Graphics;
import com.colorcorrect.main.HighScoreScreen;

public class FifteenBoardGame {

	static String colors[] = {"BLUE", "GREEN", "YELLOW", "RED","WHITE"};
	static int boardHeight, boardWidth, currentColor, boardsLeft;
	static float timerLength;
	static int board[][];
	static boolean colorShown;
	
	public FifteenBoardGame() 
	{
		boardWidth = 2;
		boardHeight = 2;
		boardsLeft = 15;
		
		createNewColor();	
		createNewBoard();
	}

	private static void createNewColor() 
	{
		currentColor = (int)(Math.random()*4);
	}

	private static void createNewBoard() 
	{
		board = new int[boardWidth][boardHeight];
		boolean colorInBoard = false;
		
		while(!colorInBoard)
		{
			for(int i=0;i<boardWidth;i++)
			{
				for(int j=0;j<boardHeight;j++)
					board[i][j] = (int)(Math.random()*4);
			}
			for(int i=0;i<boardWidth;i++)
			{
				for(int j=0;j<boardHeight;j++)
					if(board[i][j] == currentColor) colorInBoard = true;
			}	
		}
	}

	public void showColor(Game game) 
	{		
		Graphics g = game.getGraphics();
			
		g.drawRect(0, 60, 321, 320, Color.parseColor(colors[currentColor]));
	}


	public float getTimerLength() 
	{
		return timerLength;
	}

	public void showBoard(Game game) 
	{
		Graphics g = game.getGraphics();
		
		for(int i=0; i<boardHeight; i++)
		{
			for(int j=0; j<boardHeight; j++)
			{
				if(j<1 && i<1)
					g.drawRect(0, 60, 320/boardWidth, 320/boardHeight, Color.parseColor(colors[board[i][j]]));
				else if(j<1)
					g.drawRect(0, 60+320/(i*boardHeight), 320/boardWidth, 320/boardHeight, Color.parseColor(colors[board[i][j]]));
				else if(i<1)
					g.drawRect(320/(j*boardWidth), 60, 320/boardWidth, 320/boardHeight, Color.parseColor(colors[board[i][j]]));
				else
					g.drawRect(320/(j*boardWidth), 60+320/(i*boardHeight), 320/boardWidth, 320/boardHeight, Color.parseColor(colors[board[i][j]]));
			}
		}
	}

	public void clear(int i, int j, Game game) 
	{
		if(board[i][j] == currentColor || board[i][j]==4)
			board[i][j] = 4;	
		else
			boardsLeft++;
	}

	public boolean checkComplete() 
	{
		for(int i=0; i<boardHeight; i++)
		{
			for(int j=0; j<boardHeight; j++)
			{
				if(board[i][j]==currentColor)
					return false;
			}
		}
		
		createNewColor();
		createNewBoard();
		boardsLeft--;
		return true;
	}

	public int getBoardsLeft() {
		return boardsLeft;
	}

}
