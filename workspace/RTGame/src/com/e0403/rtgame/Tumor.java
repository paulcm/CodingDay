package com.e0403.rtgame;

import java.util.ArrayList;
import java.util.Random;

import com.e0403.rtgame.Cell.NeighbourPosition;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PointF;
import android.graphics.RectF;
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
	
	ArrayList<Cell> cellList;
	
	
	public Tumor(int zoomFactor){
		super();
		//paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(1.0f);
		this.zoomFactor = zoomFactor;
		tumorCentroid = new PointF();	
		r = new Random();
		
		cellList = new ArrayList<Cell>();
		
		this.composeTumor();
		RectF tb = this.getTumorBounds();
		int i=0;
	}
	
	
	public RectF getTumorBounds(){
		
		RectF cellUnion = new RectF();
		
		for(Cell c : cellList){
			cellUnion.union(c.getBounds());
		}
		
		return cellUnion;
	}
	
	
	public void composeTumor()
	{
		for(int i=0; i < 5; ++i){
			
			PointF cellCenter = new PointF(0, 0);
			
			Cell c = new Cell(zoomFactor,cellCenter);
			cellList.add(c);
		
			if(i > 0) {
				NeighbourPosition p;
				switch(i){
				case 1: p = NeighbourPosition.TOP_RIGHT;
						c.setCellCenter(1, -1);
				break;
				case 2: p = NeighbourPosition.BOTTOM_RIGHT;
						c.setCellCenter(1, 1);
				break;
				case 3: p = NeighbourPosition.BOTTOM_LEFT;
						c.setCellCenter(-1, 1);
				break;
				case 4: p = NeighbourPosition.TOP_LEFT;
						c.setCellCenter(-1, -1);
				break;
				default: p = NeighbourPosition.TOP;
						c.setCellCenter(0, 1);
				}
				cellList.get(0).addNeighbour(c, p);
			}
		}
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
			RectF r = this.getTumorBounds();
			
			bounds.set(center.x+r.left, center.y+r.top, center.x+r.right, center.y+r.bottom);// center.x+5*zoomFactor, center.y+4*zoomFactor);
		}
		
		canvas.save();
		canvas.translate(this.bounds.centerX(), this.bounds.centerY());
		for(Cell c : cellList){
			c.draw(canvas);
		}
		canvas.restore();
	}
}
