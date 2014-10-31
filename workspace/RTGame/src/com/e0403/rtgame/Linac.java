package com.e0403.rtgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Linac extends AbstractDrawableEntity{
	private float myStartPointXPos;
	private float startPointYPos;
	private float endPointXPos;
	private float endPointYPos;
	private final float myLinacWidth = 120;
	private final float myLinacHeight = 80;
	private Paint myBeamPaint;

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
	public Linac(float left, float top, float right, float bottom) {
		super();
		this.initialize(left, top, right, bottom);
	}

	
	public Linac()
	{
		
	}
	
	public void receiveTouchNotification(float xPos, float yPos)
	{
		this.endPointXPos = xPos;
		this.endPointYPos = yPos;
	}
	
	private void initialize(float left, float top, float right, float bottom) {
		// geometry
		this.bounds.set(left, top, right, bottom);
		// material
		this.paint.setColor(Color.GRAY);
		this.myStartPointXPos = left;
		this.startPointYPos = bottom;
		this.endPointXPos = right;
		this.endPointYPos = top;
		this.myBeamPaint = new Paint();
		this.myBeamPaint.setColor(Color.YELLOW);
	}

	@Override
	public void draw(Canvas canvas) {
        canvas.drawLine(myStartPointXPos + myLinacWidth, startPointYPos, endPointXPos, endPointYPos, myBeamPaint);
		canvas.drawRect(myStartPointXPos, startPointYPos - myLinacHeight/2.0f, myStartPointXPos + myLinacWidth, startPointYPos + myLinacHeight/2.0f, this.getPaint());
	}
}
