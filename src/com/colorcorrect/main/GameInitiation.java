package com.colorcorrect.main;

import com.colorcorrect.framework.AndroidGame;
import com.colorcorrect.framework.Screen;

public class GameInitiation extends AndroidGame {
	public Screen getStartScreen() {
		return new MainMenu(this);
	}
}
