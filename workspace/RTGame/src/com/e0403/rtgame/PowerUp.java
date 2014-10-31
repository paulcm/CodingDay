package com.e0403.rtgame;

import android.graphics.Canvas;
import android.graphics.Color;

public class PowerUp extends AbstractDrawableEntity{

	/**
	 * @param left
	 *            The X coordinate of the left side of the rectangle
	 * @param top
	 *            The Y coordinate of the top of the rectangle
	 * @param right
	 *            The X coordinate of the right side of the rectangle
	 * @param bottom
	 *            The Y coordinate of the bottom of the rectangle
	 */
	public PowerUp(float left, float top, float right, float bottom) {
		super();
		this.initialize(left, top, right, bottom);
	}

	
	private PowerUp()
	{
		
	}
	private void initialize(float left, float top, float right, float bottom) {
		// geometry
		this.bounds.set(left, top, right, bottom);
		// material
		this.paint.setColor(Color.WHITE);
	}
	
	public void destroy()
	{
		
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(this.getBounds(), this.getPaint());
	}
	
	

}
