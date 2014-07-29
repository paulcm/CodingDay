package com.example.bouncingball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Leaf extends AbstractDrawableEntity implements DrawableEntity {




	/**
	 * @param left The X coordinate of the left side of the rectangle
	 * @param top The Y coordinate of the top of the rectangle
	 * @param right The X coordinate of the right side of the rectangle
	 * @param bottom The Y coordinate of the bottom of the rectangle
	 */
	public Leaf(float left, float top, float right, float bottom)
	{
		this.initialize(left, top, right, bottom);
	}
	
	@Override
	public RectF getBounds() {
		return bounds;
	}

	@Override
	public Paint getPaint() {
		return paint;
	}

	
	private void initialize(float left, float top, float right, float bottom)
	{
		// geometry
		this.bounds = new RectF();
		this.bounds.set(left, top, right, bottom);
		// material
		this.paint = new Paint();
		this.paint.setColor(Color.YELLOW);
	}

	@Override
	public void draw(Canvas canvas) {
		  canvas.drawRect(this.getBounds(), this.getPaint());
	}

	
}
