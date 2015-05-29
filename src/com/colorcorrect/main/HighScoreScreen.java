package com.colorcorrect.main;

import java.util.List;

import android.graphics.Color;

import com.colorcorrect.framework.Game;
import com.colorcorrect.framework.Graphics;
import com.colorcorrect.framework.Input.TouchEvent;
import com.colorcorrect.framework.Screen;

public class HighScoreScreen extends Screen{
	public float score, gameType;

	public HighScoreScreen(Game game, float passedScore, int passedGameType) {
		super(game);
		score = passedScore;
		gameType = passedGameType;
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if(event.x> 15 && event.x<45 && event.y>440 && event.y<470)
					game.setScreen(new MainMenu(game));
			}
		}
		
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clear(Color.WHITE);
		int size = 30;
		
		if(gameType==0)
		{
			g.drawText("Time Out", 15, 45, size);
		
			if(score<10)
				g.drawText("Boards Cleared: "+(int)score, 40, 250, size);
			else if(score<100)
				g.drawText("Boards Cleared: "+(int)score, 30, 250, size);
			else
				g.drawText("Boards Cleared: "+(int)score, 20, 250, size);
		}
		else if(gameType==1)
		{
			g.drawText("All Boards Cleared", 15, 45, size);
		
			g.drawText("Time Spent: "+Math.round(score*100.0)/100.0, 40, 250, size);
			
		}
		
		g.drawText("<-",15,470, size);
		

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
