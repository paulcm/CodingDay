package com.e0403.rtgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.Log;
/** asd */
public class Leaf extends AbstractDrawableEntity {

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
	public Leaf(float left, float top, float right, float bottom) {
		super();
		this.initialize(left, top, right, bottom);
	}

	
	public Leaf()
	{
		
	}
	private void initialize(float left, float top, float right, float bottom) {
		// geometry
		this.bounds.set(left, top, right, bottom);
		// material
		this.paint.setColor(Color.CYAN);
	}

	@Override
	public void draw(Canvas canvas) {
		if(this.bounds.isEmpty())
		{
			this.paint.setColor(Color.CYAN);
			int  w = canvas.getWidth();
			int h = canvas.getHeight();
			//PointF center = new PointF(w/2, h/2);
			
			bounds.set(w-160, h-160, w, h);
		}

		canvas.drawRect(this.getBounds(), this.getPaint());
	}

}
