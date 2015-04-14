package com.e0403.rtgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Linac extends AbstractDrawableEntity{
	private float myStartPointXPos;
	private float myStartPointYPos;
	private float myEndPointXPos;
	private float myEndPointYPos;
	private float myTouchPointXPos;
	private float myTouchPointYPos;
	private final float myLinacWidth = 120;
	private final float myLinacHeight = 80;
	private float myBeamWidthMax = myLinacHeight / 2.0f;
	
	private float myBeamExitXPos;
	private float myBeamExitYPos;

	private Paint myBeamPaint;
	private Paint myCirclePaint;
    private float myBeamWidth;
	
    private Path myBoundsPath;
	
    
    public float getBeamWidth()
    {
    	return myBeamWidth;
    }
    
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
		receiveTouchNotification(0,0);
	}

	
	public Linac()
	{
		
	}
	
	public void receiveTouchNotification(float xPos, float yPos)
	{
		this.myTouchPointXPos = xPos;
		this.myTouchPointYPos = yPos;		
		
		calcEndPoint();
		
		

	}
	
	@Override
	public Path getBoundsPath() 
	{
		float x0Pos = myBeamExitXPos;
		float y0Pos = myBeamExitYPos;
		
		float x1Pos = myEndPointXPos;
		float y1Pos = myEndPointYPos;
		
		float w2 = (float) (getBeamWidth() / 2.0);
		
		
		float ortho_x =   -(y1Pos-y0Pos) / (x1Pos-x0Pos);
		float ortho_y =   1;
		
		float length = (float)  Math.sqrt(     Math.pow(ortho_x, 2) + Math.pow(ortho_y, 2)   );
		ortho_x = ortho_x / length;
		ortho_y = ortho_y / length;
		
		myBoundsPath = new Path();
		myBoundsPath.reset();
		myBoundsPath.moveTo(x0Pos+w2*ortho_x, y0Pos+w2*ortho_y);	
		myBoundsPath.lineTo(x1Pos+w2*ortho_x, y1Pos+w2*ortho_y);
		myBoundsPath.lineTo(x1Pos-w2*ortho_x, y1Pos-w2*ortho_y);
		myBoundsPath.lineTo(x0Pos-w2*ortho_x, y0Pos-w2*ortho_y);
		myBoundsPath.lineTo(x0Pos+w2*ortho_x, y0Pos+w2*ortho_y);
		
		return myBoundsPath;
	}
	
	private void initialize(float theStartPosX, float theStartPosY) {
			// material
		this.myBeamWidth = 20;
		this.myCirclePaint = new Paint(); 
		this.myStartPointXPos = theStartPosX;
		this.myBeamExitXPos = theStartPosX + myLinacWidth + 5;
		this.myStartPointYPos = theStartPosY;
		this.myBeamExitYPos = theStartPosY;
		
		this.myEndPointXPos = theStartPosX + myLinacWidth * 2.0f;
		this.myEndPointYPos = theStartPosY;
		this.myBeamPaint = new Paint(); 
		this.paint.setColor(Color.rgb(149, 165, 166)); // concrete
		this.myBeamPaint.setColor(Color.rgb(241, 196, 15)); // Sun Flower
		this.myCirclePaint.setColor(Color.rgb(52, 152, 219));// peter river
		this.myBoundsPath = new Path();
	}
	
	private void calcEndPoint()
	{
		float yStart, xStart, yEnd, xEnd, yNewEnd, xNewEnd, m;
		xNewEnd = 2000;
		xStart = myBeamExitXPos;
		yStart = myBeamExitYPos;
		xEnd = myTouchPointXPos;
		yEnd = myTouchPointYPos;
		m = (yEnd - yStart) / (xEnd - xStart);
		//yNewEnd = m * xNewEnd + yStart;
		yNewEnd = yStart + (yEnd-yStart) * ((xNewEnd-xStart)/(xEnd-xStart));
		myEndPointXPos = xNewEnd;
		myEndPointYPos = yNewEnd;
	}

	@Override
	public void draw(Canvas canvas) {
		myBeamPaint.setStrokeWidth(myBeamWidth);
		

		//canvas.drawLine(myBeamExitXPos, myBeamExitYPos, myEndPointXPos, myEndPointYPos, myBeamPaint);
        canvas.drawCircle(myStartPointXPos + myLinacWidth, myStartPointYPos, 25, myCirclePaint);
        canvas.drawRect(myStartPointXPos, myStartPointYPos - myLinacHeight/2.0f, myStartPointXPos + myLinacWidth, myStartPointYPos + myLinacHeight/2.0f, this.getPaint());
        
		Paint aDebugPaint = new Paint();
		aDebugPaint.setColor(Color.rgb(255,255,255));
		aDebugPaint.setStrokeWidth(1);
        canvas.drawPath(this.getBoundsPath(), aDebugPaint);
        
        Paint aDebugEPPaint = new Paint();
        aDebugEPPaint.setColor(Color.rgb(0,255,0));
        aDebugEPPaint.setStrokeWidth(2);
        canvas.drawPoint(this.myEndPointXPos, this.myEndPointYPos , aDebugEPPaint);
	}
}
