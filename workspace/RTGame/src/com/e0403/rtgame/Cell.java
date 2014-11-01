package com.e0403.rtgame;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

public class Cell extends AbstractDrawableEntity {

	Paint paint;
	Path path;
	int scale;
	boolean boundsInitialized = false;
	
	float triangleSide;
	float triangleSideHalf;
	float triangleHeight;

	PointF[] points = new PointF[6];
	Cell[] neighbours = new Cell[6];
	
	PointF cellCenter;
	
	enum NeighbourPosition{
		TOP,
		TOP_RIGHT,
		BOTTOM_RIGHT,
		BOTTOM,
		BOTTOM_LEFT,
		TOP_LEFT
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
	
	@Override
	public void draw(Canvas canvas) {	
		
		path.reset();
		path.moveTo(points[0].x+cellCenter.x, points[0].y+cellCenter.y);	
		path.lineTo(points[1].x+cellCenter.x, points[1].y+cellCenter.y);	
		path.lineTo(points[2].x+cellCenter.x, points[2].y+cellCenter.y);	
		path.lineTo(points[3].x+cellCenter.x, points[3].y+cellCenter.y);	
		path.lineTo(points[4].x+cellCenter.x, points[4].y+cellCenter.y);
		path.lineTo(points[5].x+cellCenter.x, points[5].y+cellCenter.y);	
		path.lineTo(points[0].x+cellCenter.x, points[0].y+cellCenter.y);	
		
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
}
	
