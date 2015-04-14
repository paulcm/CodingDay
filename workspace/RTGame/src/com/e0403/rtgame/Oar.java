package com.e0403.rtgame;

import java.util.Date;

import android.graphics.Canvas;
import android.graphics.Color;

public class Oar extends AbstractDrawableEntity{

	private boolean iHaveBeenHit;
	private Date myHitTime;
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
	public Oar(float left, float top, float right, float bottom) {
		super();
		this.initialize(left, top, right, bottom);
	}

	
	public boolean hasBeenHit()
	{
		return iHaveBeenHit;
	}
	
	public Date getHitTime()
	{
		return myHitTime;
	}
	
	private Oar()
	{
	}
	private void initialize(float left, float top, float right, float bottom) {
		myHitTime = null;
		iHaveBeenHit = false;
		// geometry
		this.bounds.set(left, top, right, bottom);
		// material
		this.paint.setColor(Color.RED);
	}
	
	
	public void markHit()
	{
		this.paint.setColor(Color.GREEN);
		myHitTime = new Date();
		iHaveBeenHit = true;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawPath(this.getBoundsPath(), this.getPaint());			
	}
	
	

}
