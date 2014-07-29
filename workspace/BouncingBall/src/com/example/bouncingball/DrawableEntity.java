package com.example.bouncingball;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public interface DrawableEntity {

	
	/**
	 * Returns the bounds of a Leaf
	 * 
	 * @return the bounding box
	 */
	public RectF getBounds();
	
	public Paint getPaint();
	
	public void draw(Canvas canvas);
}
