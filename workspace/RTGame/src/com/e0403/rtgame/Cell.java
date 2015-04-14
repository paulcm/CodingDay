package com.e0403.rtgame;


import java.util.Random;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Op;
import android.graphics.PointF;
import android.os.Build;

public class Cell extends AbstractLivingEntitiy {

	Paint paint;
	Path path;
	int scale;
	boolean boundsInitialized = false;
	float triangleSide;
	float triangleSideHalf;
	float triangleHeight;
	private static Random RANDOM = new Random();

	PointF[] points = new PointF[6];
	Cell[] neighbours = new Cell[6];
	
	PointF cellCenter;
	
	// Cell dies after           x seconds of irradiation
	int health = (int) Math.ceil(    0.4 * ( 1000 / MainView.INTERVAL * 10)   );	
	enum NeighbourPosition{
		TOP,
		TOP_RIGHT,
		BOTTOM_RIGHT,
		BOTTOM,
		BOTTOM_LEFT,
		TOP_LEFT;
		
		 public static NeighbourPosition fromInteger(int x) {
		        switch(x) 
		        {
		        case 0:
		            return TOP;
		        case 1:
		            return TOP_RIGHT;
		        case 2:
		        	return BOTTOM_RIGHT;
		        case 3:
		        	return BOTTOM;
		        case 4:
		        	return BOTTOM_LEFT;
		        case 5:
		        	return TOP_LEFT;
		        default:
		        	return null;
		        }
		    }
		
		
	}
	
	public Cell(int zoomFactor, PointF cellCenter){
		super();
		this.scale = zoomFactor;
		this.cellCenter = cellCenter;
		
		triangleSide = (float)(Math.sqrt(1f/3f)) * scale;
		triangleSideHalf = triangleSide / 2;
		triangleHeight = (float)(Math.sqrt(3f/4f)*triangleSide);
		
		path = new Path();
	
		//  Generation of cell path points
		//      5 -- 0
		//	   / \  / \
		//    4---\/---1
		//     \  /\  /
		//      3 -- 2     
		points[0] = new PointF(triangleSideHalf,-triangleHeight);
		points[1] = new PointF(triangleSide,0);
		points[2] = new PointF(triangleSideHalf, triangleHeight);
		points[3] = new PointF(-triangleSideHalf,triangleHeight);
		points[4] = new PointF(-triangleSide,0);
		points[5] = new PointF(-triangleSideHalf,-triangleHeight);
		
		float boundLeft = points[4].x;
		float boundTop = points[5].y;
		float boundRight = points[1].x;
		float boundBottom = points[2].y;
		
		
		this.bounds.set(boundLeft,boundTop,boundRight,boundBottom);
		
		paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(0);
		
	}
	
	
	public void updatePath() {
		path.reset();
		path.moveTo(points[0].x+cellCenter.x, points[0].y+cellCenter.y);	
		path.lineTo(points[1].x+cellCenter.x, points[1].y+cellCenter.y);	
		path.lineTo(points[2].x+cellCenter.x, points[2].y+cellCenter.y);	
		path.lineTo(points[3].x+cellCenter.x, points[3].y+cellCenter.y);	
		path.lineTo(points[4].x+cellCenter.x, points[4].y+cellCenter.y);
		path.lineTo(points[5].x+cellCenter.x, points[5].y+cellCenter.y);	
		path.lineTo(points[0].x+cellCenter.x, points[0].y+cellCenter.y);	
	}
	
	@Override
	public void draw(Canvas canvas) {	
		updatePath();		
		canvas.drawPath(path, paint);
	}
	
	public PointF getCellCenter()
	{
		return this.cellCenter;	
	}
	
	public void setCellCenter(float x, float y)
	{
		PointF cco = new PointF(this.cellCenter.x + x, this.cellCenter.y + y);
		
		this.bounds.offset(cco.x,cco.y);
		this.cellCenter.set(x, y);
		updatePath();
	}
	
	public PointF getNeighbourCellCenter(NeighbourPosition pos) {
		PointF retVal = new PointF();	
		
		switch(pos) {
		case TOP:
			retVal.set(cellCenter.x+0, cellCenter.y - triangleHeight*2);
			break;
		case TOP_LEFT:
			retVal.set(cellCenter.x - triangleSide - triangleSideHalf, cellCenter.y - triangleHeight);
			break;
		case TOP_RIGHT:
			retVal.set(cellCenter.x + triangleSide + triangleSideHalf, cellCenter.y - triangleHeight);
			break;
		case BOTTOM:
			retVal.set(cellCenter.x+0, cellCenter.y+triangleHeight*2);
			break;
		case BOTTOM_LEFT:
			retVal.set(cellCenter.x - triangleSide - triangleSideHalf, cellCenter.y + triangleHeight);
			break;
		case BOTTOM_RIGHT:
			retVal.set(cellCenter.x + triangleSide + triangleSideHalf, cellCenter.y + triangleHeight);
			break;
		}
		
		return retVal;
	}
	
	@TargetApi(Build.VERSION_CODES.KITKAT) 
	public boolean irradiate(android.graphics.Path beam){
		Path.Op op = Op.INTERSECT;
		
		boolean isIrradiated = path.op(beam, path , op); //path.op(beam, android.graphics.Path.Op.INTERSECT);
		
		if(isIrradiated){
			--health;
			if(health <= 0)
			{
				kill();
			}
		}
		
		return isIrradiated;
	}
	
	public void notifyDeath(Cell theDeadCell)
	{
		for(int i = 0; i < 6; ++i)
		{
			if(neighbours[i] != null && neighbours[i].equals(theDeadCell))
			{
				neighbours[i] = null;
			}
		}
	}
	
	@Override
	public void kill()
	{
		super.kill();
		paint.setColor(Color.BLACK);
		for(int i = 0; i < 6; ++i)
		{
			if(hasNeighbour(NeighbourPosition.fromInteger(i)))
			{
				notifyDeath(this);
			}
		}
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
		this.cellCenter.offset(dx,dy);
		this.bounds.offset(dx, dy);
	}
	
	public Cell divide(float prob)
	{
		float val = RANDOM.nextFloat();
		if(prob >= val)
		{
			int posAsInt = RANDOM.nextInt(5);
			NeighbourPosition pos = NeighbourPosition.fromInteger(posAsInt);
			NeighbourPosition.values();
			Cell cell = new Cell(this.scale, getNeighbourCellCenter(pos));
			addNeighbour(cell, pos);
			return cell;
		}
		else
		{
			return null;
		}
	
	}
}
	
