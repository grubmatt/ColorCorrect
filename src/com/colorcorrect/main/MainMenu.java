package com.colorcorrect.main;

import java.util.List;

import android.graphics.Color;

import com.colorcorrect.fifteenboard.FifteenBoardScreen;
import com.colorcorrect.framework.Game;
import com.colorcorrect.framework.Graphics;
import com.colorcorrect.framework.Screen;
import com.colorcorrect.framework.Input.TouchEvent;
import com.colorcorrect.tensecond.TenSecondScreen;


public class MainMenu extends Screen {
	
	public MainMenu(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if(event.x> 0 && event.x<161 && event.y>0 && event.y<251)
				{
					game.setScreen(new TenSecondScreen(game));
				}
				else if(event.x> 160 && event.x<500 && event.y>0 && event.y<251)
				{
					game.setScreen(new FifteenBoardScreen(game));
				}
				else if(event.x> 0 && event.x<161 && event.y>250 && event.y<500)
				{
					game.setScreen(new HelpScreen(game));
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawRect(0, 0, 161, 251, Color.RED);
		g.drawRect(160, 0, 161, 251, Color.BLUE);
		g.drawRect(0, 250, 161, 251, Color.YELLOW);
		g.drawRect(160, 250, 161, 251, Color.GREEN);
		
		int size = 30;
		g.drawText("10 Sec", 35, 125, size);
		g.drawText("15 Board", 180, 125, size);
		g.drawText("Help", 45, 375, size);
		
		size = 45;
		g.drawText(" Color", 166, 355, size);
		g.drawText("Correct", 166, 395, size);
	

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
