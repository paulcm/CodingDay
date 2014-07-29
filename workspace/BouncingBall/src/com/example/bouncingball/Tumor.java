package com.example.bouncingball;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class Tumor extends Drawable {
	
	public float tumorWidth; // tumor's radius
	public float tumorHeight; // tumor's radius
	public float tumorXCenter; // tumor's center (x,y)
	public float tumorYCenter; // tumorRadius + 40;
	PointF tumorCentroid;
	public float tumorSpeedX = 6;  // tumor's speed (x,y)
	public float tumorSpeedY = 4;
	public RectF tumorBounds;      // Needed for Canvas.drawOval
	
	int zoomFactor = 20;
	
	Bitmap tumorBitmap;
	
	ColorFilter colorFilter;
	int alpha = 255;
	int opacity = 1;
	
	
	public Tumor(Context context){
	
		tumorCentroid = new PointF(100,100);
	}


	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		int  w = canvas.getWidth();
		int h = canvas.getHeight();
		
		tumorCentroid.set(w/2,h/2);

		// middle line
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(1.0f);
		
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


	@Override
	public int getOpacity() {
		return PixelFormat.OPAQUE;
	}


	@Override
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}


	@Override
	public void setColorFilter(ColorFilter cf) {
		this.colorFilter = cf;
		
	}
	
}
