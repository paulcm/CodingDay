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
	
	float fl;
	float hl;
	float fh;
	
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
		
		fl = (float)(Math.sqrt(1f/3f));
		hl = fl / 2;
		fh = (float)(Math.sqrt(3f/4f)*fl);
		
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

		this.bounds.set(cellCenter.x - fl*scale, cellCenter.y - fh*scale, cellCenter.x + fl*scale, cellCenter.y + fh*scale);
	}
	
	@Override
	public void draw(Canvas canvas) {	
		canvas.save();
		canvas.translate(this.cellCenter.x, this.cellCenter.x);
		canvas.drawPath(path, paint);
		canvas.restore();
	}
	
	public PointF getCellCenter()
	{
		return this.cellCenter;	
	}
	
	public void setCellCenter(float x, float y)
	{
		float shiftx = x * fl * 2 * scale;
		float shifty = y * fh * 2 * scale;
		
		PointF cco = new PointF(this.cellCenter.x + shiftx, this.cellCenter.y + shifty);
		
		this.bounds.offset(cco.x,cco.y);
		this.cellCenter.set(shiftx, shifty);
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
	
