package com.e0403.rtgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Linac extends AbstractDrawableEntity{
	private float myStartPointXPos;
	private float myStartPointYPos;
	private float myEndPointXPos;
	private float myEndPointYPos;
	private final float myLinacWidth = 120;
	private final float myLinacHeight = 80;
	private float myBeamWidthMax = myLinacHeight / 2.0f;
	private Paint myBeamPaint;

	/**
	 * @param theStartPosX
	 *            The X coordinate
	 * @param theStartPosY
	 *            The Y coordinate
	 */
	public Linac(float theStartPosX, float theStartPosY) {
		super();
		this.initialize(theStartPosX, theStartPosY);
	}

	
	public Linac()
	{
		
	}
	
	public void receiveTouchNotification(float xPos, float yPos)
	{
		this.myEndPointXPos = xPos;
		this.myEndPointYPos = yPos;
		calcEndPoint();
	}
	
	private void initialize(float theStartPosX, float theStartPosY) {
			// material
		this.paint.setColor(Color.GRAY);
		this.myStartPointXPos = theStartPosX;
		this.myStartPointYPos = theStartPosY;
		this.myEndPointXPos = theStartPosX + myLinacWidth * 2.0f;
		this.myEndPointYPos = theStartPosY;
		this.myBeamPaint = new Paint();
		this.myBeamPaint.setColor(Color.YELLOW);
	}
	
	private void calcEndPoint()
	{
		float yStart, xStart, yEnd, xEnd, yNewEnd, xNewEnd, m;
		xNewEnd = 10000;
		xStart = myStartPointXPos;
		yStart = myStartPointYPos;
		xEnd = myEndPointXPos;
		yEnd = myEndPointYPos;
		m = (yEnd - yStart) / (xEnd - xStart);
		yNewEnd = m * (xNewEnd + xStart) - yStart;
		myEndPointXPos = xNewEnd;
		myEndPointYPos = yNewEnd;
	}

	@Override
	public void draw(Canvas canvas) {
		myBeamPaint.setStrokeWidth(20);

        canvas.drawLine(myStartPointXPos + myLinacWidth - 5, myStartPointYPos, myEndPointXPos, myEndPointYPos, myBeamPaint);
        canvas.drawRect(myStartPointXPos, myStartPointYPos - myLinacHeight/2.0f, myStartPointXPos + myLinacWidth, myStartPointYPos + myLinacHeight/2.0f, this.getPaint());
	}
}
