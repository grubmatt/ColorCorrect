package com.colorcorrect.framework;

import com.colorcorrect.framework.Graphics.PixmapFormat;

public interface Pixmap
{
	public int getWidth();
	
	public int getHeight();
	
	public PixmapFormat getFormat();
	
	public void dispose();
}
