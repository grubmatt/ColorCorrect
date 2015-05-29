package com.colorcorrect.main;

import com.colorcorrect.framework.AndroidGame;
import com.colorcorrect.framework.Screen;

public class AndroidArcade extends AndroidGame {
	public Screen getStartScreen() {
		return new MainMenu(this);
	}
}
