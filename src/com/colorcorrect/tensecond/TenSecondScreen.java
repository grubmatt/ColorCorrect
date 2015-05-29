package com.colorcorrect.tensecond;

import java.util.List;

import android.graphics.Color;

import com.colorcorrect.framework.Game;
import com.colorcorrect.framework.Graphics;
import com.colorcorrect.framework.Screen;
import com.colorcorrect.framework.Input.TouchEvent;
import com.colorcorrect.main.HighScoreScreen;
import com.colorcorrect.main.MainMenu;


public class TenSecondScreen extends Screen{
	TenSecondGame tenSecond;
	boolean showColor, initialized, timePenalty;
	static float timeLeft;
	
	public TenSecondScreen(Game game)
	{
		super(game);
		tenSecond = new TenSecondGame();
		showColor = true;
		timeLeft=tenSecond.getTimerLength();
		initialized = false;
		timePenalty = false;
	}

	@Override
	public void update(float deltaTime) {	
		if(initialized)
			timeLeft -= .01;
		
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				
				if(event.x> 15 && event.x<45 && event.y>440 && event.y<470)
				{
					game.setScreen(new MainMenu(game));
					i=len;
				}
				else if(!initialized)
				{
					initialized = true;
					showColor = false;
				}
				else if(showColor)
					showColor=false;
				else if(event.x>0 && event.x<160 && event.y>60 && event.y<220)
					timePenalty = tenSecond.clear(0,0, game);
				else if(event.x>160 && event.x<320 && event.y>60 && event.y<220)
					timePenalty = tenSecond.clear(0,1, game);
				else if(event.x>0 && event.x<160 && event.y>220 && event.y<380)
					timePenalty = tenSecond.clear(1,0, game);
				else if(event.x>160 && event.x<320 && event.y>220 && event.y<380)
					timePenalty = tenSecond.clear(1,1, game);
			}
		}	
		
		if(timePenalty)
		{
			timePenalty = false;
			timeLeft -= 1;
		}
		
		if(tenSecond.checkComplete())
			showColor = true;
		if(timeLeft<=0)
			game.setScreen(new HighScoreScreen(game, tenSecond.getScore(), 0));			
}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clear(Color.WHITE);
		
		int size = 35;
		g.drawText("Color Correct", 15, 45, size);
		
		g.drawRect(0, 60, 321, 320, Color.BLACK);
		if(showColor && !initialized)
		{
			tenSecond.showColor(game);
			g.drawText("Tap to start", 70, 230, size);
		}
		else if(showColor)
			tenSecond.showColor(game);
		else
			tenSecond.showBoard(game);
		
		
		g.drawText("<-",15,470, size);
		g.drawText("Time Left: "+timeLeft, 5, 420, size);
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
