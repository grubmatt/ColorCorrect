package com.colorcorrect.framework;

import com.colorcorrect.main.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.os.Bundle;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

@SuppressLint("NewApi")
public abstract class AndroidGame extends Activity implements Game {
	AndroidFastRenderView renderView;
	Graphics graphics;
	Input input;
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;
	
	private AdView adView;
	private static final String AD_UNIT_ID = "ca-app-pub-2328575593132721/6454404098";
	private Point screenSize;

	
	@Override
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);    
		
		// finds orientation then sets bitmap accordingly
		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 480 : 320;
		int frameBufferHeight = isLandscape ? 320 : 480;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
				frameBufferHeight, Config.RGB_565);
		
		screenSize = new Point();
		getWindowManager().getDefaultDisplay().getSize(screenSize);
		
		int adHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
		int windowWidth = screenSize.x;
		int windowHeight = screenSize.y - adHeight;
		
		// scales to screen size
		float scaleX = (float) frameBufferWidth
				/ windowWidth;
		float scaleY = (float) frameBufferHeight
				/ windowHeight;
		
		// Implements necessary parts
		renderView = new AndroidFastRenderView(this, frameBuffer, windowWidth, windowHeight);
		graphics = new AndroidGraphics(getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);    

		// Create ad
		adView = new AdView(this);
		adView.setAdSize(AdSize.BANNER);
		adView.setAdUnitId(AD_UNIT_ID);

		// Create an ad request. Check logcat output for the hashed device ID to
		// get test ads on a physical device.
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("E93A4182BE825C3889A861DC1A7DFAA").build();

		adView.loadAd(adRequest);

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(renderView);
	    layout.addView(adView);
	    
	    
		screen = getStartScreen();
		setContentView(layout);
		
		// keeps screen from sleeping
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		screen.resume();
		renderView.resume();
		if (adView != null) {
		      adView.resume();
		    }
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (adView != null) {
			adView.pause();
		}
		renderView.pause();
		screen.pause();
		if (isFinishing())
			screen.dispose();
		
	}
	
	@Override
	public Input getInput() {
		return input;
	}

	@Override
	public FileIO getFileIO() {
		return fileIO;
	}

	@Override
	public Graphics getGraphics() {
		return graphics;
	}
	
	@Override
	public void setScreen(Screen screen) {
		if (screen == null)
			throw new IllegalArgumentException("Screen must not be null");
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}
	
	@Override
	public Screen getCurrentScreen() {
		return screen;
	}
}