package com.e0403.rtgame;

import java.util.ArrayList;
import java.util.Random;

import com.e0403.rtgame.Cell.NeighbourPosition;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.Path.Op;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.KITKAT) public class Tumor extends AbstractDrawableEntity {
	
	PointF tumorCentroid;
	private float yMovement = 1;
	private float xMovement = 1;
	final static int movementStepsize = 5;
	private long performedMovements = 0;
	private int accelerationFactor = 1;
	Random r;
	int zoomFactor = 1;
	int speed = 1;
	Cell cell;
	
	double moveIterator = 0;
	
	private float lastYPos = 0;
	private float heightFactor = 1;
	
	
	ArrayList<Cell> cellList;
	
	
	public Tumor(int zoomFactor, int speed){
		super();
		//paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(1.0f);
		this.zoomFactor = zoomFactor;
		this.speed = speed;
		tumorCentroid = new PointF();	
		r = new Random();
		
		cellList = new ArrayList<Cell>();
		
		this.composeTumor();
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
		cellList.clear();
		
		Cell middleCell = new Cell(zoomFactor, new PointF(0,0));
		Cell topRight = new Cell(zoomFactor, middleCell.getNeighbourCellCenter(NeighbourPosition.TOP_RIGHT));
		Cell bottomRight = new Cell(zoomFactor, middleCell.getNeighbourCellCenter(NeighbourPosition.BOTTOM_RIGHT));
		Cell bottomLeft = new Cell(zoomFactor, middleCell.getNeighbourCellCenter(NeighbourPosition.BOTTOM_LEFT));
		Cell topLeft = new Cell(zoomFactor, middleCell.getNeighbourCellCenter(NeighbourPosition.TOP_LEFT));
		Cell top = new Cell(zoomFactor, middleCell.getNeighbourCellCenter(NeighbourPosition.TOP));
		Cell bottom = new Cell(zoomFactor, middleCell.getNeighbourCellCenter(NeighbourPosition.BOTTOM));
		
		cellList.add(middleCell);
		cellList.add(topRight);
		cellList.add(bottomRight);
		cellList.add(bottomLeft);
		cellList.add(topLeft);
		cellList.add(top);
		cellList.add(bottom);
		
	}
	
	@SuppressLint("NewApi") public void moveTumor(float xMin, float yMin, float xMax, float yMax)
	{	
		if(xMin != xMax && yMin != yMax){
			
			float ySpan = yMax-yMin;
			
			Path p = new Path();
			float relYPos =  (float)Math.sin(moveIterator*speed);
		
			if(Math.abs(moveIterator % (2 * Math.PI)) <= 0.1)
			{				
   			  heightFactor = (float)(1.0 - (float)(r.nextInt(9))/ 10);
			}
			
			float absYPos =  heightFactor * (ySpan / 2) * relYPos;
			
			for(Cell c : cellList){
				c.setCellCenter(c.cellCenter.x, c.cellCenter.y + (float)absYPos - lastYPos);
			}
			lastYPos = (float)absYPos;
			moveIterator += 2 * Math.PI / (MainLoopThread.INTERVAL * 10);
			moveIterator = moveIterator % (2 * Math.PI);
			Log.i("moveIterator: ", ""+moveIterator);
		}

	}

	


	
	@Override
	public void draw(Canvas canvas) {
		
			if(this.bounds.isEmpty())
			{
				int  w = canvas.getWidth();
				int h = canvas.getHeight();
				PointF center = new PointF(w/2, h/2);
				
				for(Cell c : cellList){
					c.setCellCenter(center.x + c.cellCenter.x, center.y + c.cellCenter.y);
				}
				
				this.bounds.set(this.getTumorBounds());
			}
			for(Cell c : cellList){
				c.draw(canvas);
			}
	}
}
