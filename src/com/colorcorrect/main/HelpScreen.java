package com.colorcorrect.main;

import java.util.List;

import android.graphics.Color;

import com.colorcorrect.framework.Game;
import com.colorcorrect.framework.Graphics;
import com.colorcorrect.framework.Screen;
import com.colorcorrect.framework.Input.TouchEvent;

public class HelpScreen extends Screen {

	public HelpScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
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
		int size = 45;	
		
		g.drawText("Help", 15, 45, size);
		size = 30;
		g.drawText("1. Memorize color", 30,110,size);
		g.drawText("     that is shown.", 30,150,size);
		
		g.drawText("2. Tap all of that", 30,220,size);
		g.drawText("     color on the", 30,260,size);
		g.drawText("     following board.", 30,300,size);
		
		g.drawText("3. Repeat until you", 30,370,size);
		g.drawText("     run out of time", 30,400,size);
		g.drawText("     or boards.", 30,430,size);
	
		

		
		g.drawText("<-",15,470, size);

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
