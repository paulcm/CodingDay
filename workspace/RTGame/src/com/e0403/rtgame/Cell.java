package com.e0403.rtgame;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;


public class Cell extends AbstractDrawableEntity {

	Paint paint;
	Path path;
	int scale;
	boolean boundsInitialized = false;
	
	Cell[] neighbours = new Cell[6];
	
	enum NeighbourPosition{
		TOP,
		TOP_RIGHT,
		BOTTOM_RIGHT,
		BOTTOM,
		BOTTOM_LEFT,
		TOP_LEFT
	}
	
	public Cell(int zoomFactor){
		super();
		this.scale = zoomFactor;
		
		float fl = (float)(Math.sqrt(1f/3f));
		float hl = fl / 2;
		float fh = (float)(Math.sqrt(3f/4f)*fl);
		
		path = new Path();
	
		path.moveTo(hl*scale, fh*scale);
		path.lineTo(fl*scale, 0);
		path.lineTo(hl*scale,-fh*scale);
		path.lineTo(-hl*scale,-fh*scale);
		path.lineTo(-fl*scale, 0);
		path.lineTo(-hl*scale,fh*scale);
		path.lineTo(hl*scale,fh*scale);
		
		paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(0);

		this.bounds.set(-fl*scale, -fh*scale, fl*scale, fh*scale);
	}
	
	@Override
	public void draw(Canvas canvas) {	
		
		if(! boundsInitialized)
		{
			int  w = canvas.getWidth();
			int h = canvas.getHeight();
			
			PointF center = new PointF(w/2, h/2);
			bounds.offset(center.x, center.y);
			
			boundsInitialized = true;
		}
		
		canvas.save();
		canvas.translate(this.bounds.centerX(), this.bounds.centerY());
		canvas.drawPath(path, paint);
		canvas.restore();
	}

	boolean hasNeighbour(NeighbourPosition pos) {
		return neighbours[pos.ordinal()] != null;		
	}
	
	boolean addNeighbour(Cell cell, NeighbourPosition pos) {
		if( ! hasNeighbour(pos))
		{
			neighbours[pos.ordinal()] = cell;
			return true;
		}
		
		return false;
	}
	
	@Override
	public void move(float dx, float dy) {
		this.bounds.offset(dx, dy);
		Log.i("TAG",this.bounds+"");
	}
	
	public void moveCell(int xMin, int yMin, int xMax, int yMax)
	{
//		accelerationFactor = r.nextInt(6)+1;
//		
//		boolean collision[] = this.collideAndCorrect((int)xMovement, (int)yMovement, xMin, yMin, xMax, yMax);
//		
//		if (collision[0] || collision[1])
//			accelerationFactor = r.nextInt(6)+1;
//		
//		if (collision[0])
//		{					
//			if (xMovement < 0)
//				xMovement = movementStepsize;
//			else
//				xMovement = movementStepsize * (-1);
//					
//			xMovement = xMovement * (float)accelerationFactor;
//			
//		}
//		if (collision[1])
//		{	
//			if (yMovement < 0)
//				yMovement = movementStepsize;
//			else
//				yMovement = movementStepsize  * (-1);
//			
//			yMovement = yMovement * (float)accelerationFactor;
//		}
//		performedMovements++;
	}
}
