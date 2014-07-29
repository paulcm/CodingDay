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
	
	public void move(float x, float y);

	void collideAndCorrect(float dx, float dy, float xMin, float yMin,
			float xMax, float yMax);
}
