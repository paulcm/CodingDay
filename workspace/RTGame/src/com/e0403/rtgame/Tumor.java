package com.e0403.rtgame;

import java.util.ArrayList;
import java.util.Random;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;

import com.e0403.rtgame.Cell.NeighbourPosition;

@TargetApi(Build.VERSION_CODES.KITKAT) public class Tumor extends AbstractDrawableEntity {
	
	PointF tumorCentroid;
	final static int movementStepsize = 5;

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

		this.zoomFactor = zoomFactor;
		this.speed = speed;
		tumorCentroid = new PointF();	
		r = new Random();
		
		cellList = new ArrayList<Cell>();
		
		this.composeTumor();
	}
	
	public boolean irradiate(Path beam){
		
		boolean irradiated = false;
		for(Cell c : cellList) {
			if(c.irradiate(beam))
				irradiated = true;
		}
		return irradiated;
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
	
	public void moveTumor(float xMin, float yMin, float xMax, float yMax)
	{	
		if(xMin != xMax && yMin != yMax){
			
			float ySpan = yMax-yMin;
			
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
			// set amount of sine curves (param speed) is processed in 10 seconds
			moveIterator += 2 * Math.PI / (1000 / MainView.INTERVAL * 10);
			moveIterator = moveIterator % (2 * Math.PI);
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		
			if(this.bounds.isEmpty())
			{
				int w = canvas.getWidth();
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
