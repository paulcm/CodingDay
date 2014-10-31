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
	private Paint myCirclePaint;
    private float myBeamWidth;
	
	public void setBeamWidth(float width)
	{
		this.myBeamWidth = width;
	}
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
		this.bounds.left = myStartPointXPos;
		this.bounds.right = myEndPointXPos;
		if(myStartPointYPos > myEndPointYPos)
		{
			this.bounds.bottom = myStartPointYPos;
			this.bounds.top = myEndPointYPos;
		}
		else
		{
			this.bounds.bottom = myEndPointYPos;
			this.bounds.top = myStartPointYPos;
		}
		
				
		calcEndPoint();
	}
	
	private void initialize(float theStartPosX, float theStartPosY) {
			// material
		this.myBeamWidth = 20;
		this.myCirclePaint = new Paint(); 
		this.myStartPointXPos = theStartPosX;
		this.myStartPointYPos = theStartPosY;
		this.myEndPointXPos = theStartPosX + myLinacWidth * 2.0f;
		this.myEndPointYPos = theStartPosY;
		this.myBeamPaint = new Paint(); 
		this.paint.setColor(Color.rgb(149, 165, 166)); // concrete
		this.myBeamPaint.setColor(Color.rgb(241, 196, 15)); // Sun Flower
		this.myCirclePaint.setColor(Color.rgb(52, 152, 219));// peter river
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
		myBeamPaint.setStrokeWidth(myBeamWidth);

        canvas.drawLine(myStartPointXPos + myLinacWidth - 5, myStartPointYPos, myEndPointXPos, myEndPointYPos, myBeamPaint);
        canvas.drawCircle(myStartPointXPos + myLinacWidth, myStartPointYPos, 25, myCirclePaint);
        canvas.drawRect(myStartPointXPos, myStartPointYPos - myLinacHeight/2.0f, myStartPointXPos + myLinacWidth, myStartPointYPos + myLinacHeight/2.0f, this.getPaint());
	}
}
