package com.e0403.rtgame;

import java.lang.System;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PointF;
//import android.provider.Settings.System;
import android.util.Log;

public class Tumor extends AbstractDrawableEntity {
	
	public float tumorWidth; // tumor's radius
	public float tumorHeight; // tumor's radius
	public float tumorXCenter; // tumor's center (x,y)
	public float tumorYCenter; // tumorRadius + 40;
	PointF tumorCentroid;
	public float tumorSpeedX = 6;  // tumor's speed (x,y)
	public float tumorSpeedY = 4;    // Needed for Canvas.drawOval
	private float yMovement = 1;
	private float xMovement = 1;
	final static int movementStepsize = 5;
	private long performedMovements = 0;
	private int accelerationFactor = 1;
	Random r;
	int zoomFactor = 1;
	
	Bitmap tumorBitmap;
	
	ColorFilter colorFilter;
	int alpha = 255;
	int opacity = 1;
	
	
	public Tumor(int zoomFactor){
		super();
		//paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(1.0f);
		this.zoomFactor = zoomFactor;
		tumorCentroid = new PointF();	
		r = new Random();
		 
	}
	
	public void moveTumor(int xMin, int yMin, int xMax, int yMax)
	{
		
		boolean collision[] = this.collideAndCorrect((int)xMovement, (int)yMovement, xMin, yMin, xMax, yMax);
		
		if (collision[0] || collision[1])
			accelerationFactor = r.nextInt(6)+1;
		
		if (collision[0])
		{					
			if (xMovement < 0)
				xMovement = movementStepsize;
			else
				xMovement = movementStepsize * (-1);
					
			xMovement = xMovement * (float)accelerationFactor;
			
		}
		if (collision[1])
		{	
			if (yMovement < 0)
				yMovement = movementStepsize;
			else
				yMovement = movementStepsize  * (-1);
			
			yMovement = yMovement * (float)accelerationFactor;
		}
		performedMovements++;
	}

	

	@Override
	public void draw(Canvas canvas) {
		Log.i("Tumor: ", "Bounds:" + "Left: " + this.getBounds().left + "Top: "
				+ this.getBounds().top + "right: " + this.getBounds().right
				+ "bottom: " + this.getBounds().bottom);
		if(this.bounds.isEmpty())
		{
			int  w = canvas.getWidth();
			int h = canvas.getHeight();
			
			PointF center = new PointF(w/2, h/2);
			bounds.set(center.x-5*zoomFactor, center.y-4*zoomFactor, center.x+5*zoomFactor, center.y+4*zoomFactor);
		}

		Log.i("Tumor: ", "Bounds Center:" + " x: " + this.bounds.centerX() + " y: " + this.bounds.centerY());
		tumorCentroid.set(bounds.centerX(),bounds.centerY());

		// middle line
		
		canvas.drawRect(tumorCentroid.x-3*zoomFactor, tumorCentroid.y-4*zoomFactor, tumorCentroid.x-2*zoomFactor, tumorCentroid.y-3*zoomFactor, paint);
		canvas.drawRect(tumorCentroid.x+2*zoomFactor, tumorCentroid.y-4*zoomFactor, tumorCentroid.x+3*zoomFactor, tumorCentroid.y-3*zoomFactor, paint);
		
		canvas.drawRect(tumorCentroid.x-2*zoomFactor, tumorCentroid.y-3*zoomFactor, tumorCentroid.x-1*zoomFactor, tumorCentroid.y-2*zoomFactor, paint);
		canvas.drawRect(tumorCentroid.x+1*zoomFactor, tumorCentroid.y-3*zoomFactor, tumorCentroid.x+2*zoomFactor, tumorCentroid.y-2*zoomFactor, paint);
		
		canvas.drawRect(tumorCentroid.x-3*zoomFactor, tumorCentroid.y-2*zoomFactor, tumorCentroid.x+3*zoomFactor, tumorCentroid.y-1*zoomFactor, paint);
		//left eye
		canvas.drawRect(tumorCentroid.x-4*zoomFactor, tumorCentroid.y-1*zoomFactor, tumorCentroid.x-3*zoomFactor, tumorCentroid.y-0*zoomFactor, paint);
		//middle
		canvas.drawRect(tumorCentroid.x-1*zoomFactor, tumorCentroid.y-1*zoomFactor, tumorCentroid.x+1*zoomFactor, tumorCentroid.y-0*zoomFactor, paint);
		//right eye
		canvas.drawRect(tumorCentroid.x+3*zoomFactor, tumorCentroid.y-1*zoomFactor, tumorCentroid.x+4*zoomFactor, tumorCentroid.y-0*zoomFactor, paint);
		// center line		
		canvas.drawRect(tumorCentroid.x-5*zoomFactor, tumorCentroid.y, tumorCentroid.x+5*zoomFactor, tumorCentroid.y+1*zoomFactor, paint);
		canvas.drawRect(tumorCentroid.x-3*zoomFactor, tumorCentroid.y+1*zoomFactor, tumorCentroid.x+3*zoomFactor, tumorCentroid.y+2*zoomFactor, paint);
		
		canvas.drawRect(tumorCentroid.x-5*zoomFactor, tumorCentroid.y+1*zoomFactor, tumorCentroid.x-4*zoomFactor, tumorCentroid.y+3*zoomFactor, paint);
		
		canvas.drawRect(tumorCentroid.x-3*zoomFactor, tumorCentroid.y+2*zoomFactor, tumorCentroid.x-2*zoomFactor, tumorCentroid.y+3*zoomFactor, paint);
		canvas.drawRect(tumorCentroid.x+2*zoomFactor, tumorCentroid.y+2*zoomFactor, tumorCentroid.x+3*zoomFactor, tumorCentroid.y+3*zoomFactor, paint);
		
		canvas.drawRect(tumorCentroid.x+4*zoomFactor, tumorCentroid.y+1*zoomFactor, tumorCentroid.x+5*zoomFactor, tumorCentroid.y+3*zoomFactor, paint);
		canvas.drawRect(tumorCentroid.x-2*zoomFactor, tumorCentroid.y+3*zoomFactor, tumorCentroid.x-1*zoomFactor, tumorCentroid.y+4*zoomFactor, paint);
		canvas.drawRect(tumorCentroid.x+1*zoomFactor, tumorCentroid.y+3*zoomFactor, tumorCentroid.x+2*zoomFactor, tumorCentroid.y+4*zoomFactor, paint);	
	
	}
	
}
